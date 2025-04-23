package kr.co.kurly.feature.home

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kr.co.kurly.core.domain.GetProductsUseCaseImpl
import kr.co.kurly.test.testing.repository.TestProductRepository
import kr.co.kurly.test.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

  private lateinit var viewModel: HomeViewModel
  private val productRepository = TestProductRepository()
  private val getProductUseCase = GetProductsUseCaseImpl(productRepository)

  @Before
  fun setup() {
    viewModel = HomeViewModel(
      productRepository = productRepository,
      getProductsUseCase = getProductUseCase
    )

  }

  private fun TestScope.baseSideEffectJob(): Job = launch {
    viewModel.uiSideEffect.collect { effect ->
      when (effect) {
        is HomeUiSideEffect.Load -> {
          viewModel.fetchData(effect)
        }
      }
    }
  }

  @Test
  fun whenAppFirstLoading_thenSetNoneType() = runTest {
    val job = Job()

    advanceUntilIdle()
    val expected = viewModel.uiState.value.uiType
    val actual = HomeUiType.NONE

    assertEquals(
      expected,
      actual
    )

    job.cancel()
  }

  @Test
  fun whenAppFirstLoaded_thenSetLoadedType() = runTest {
    val job = baseSideEffectJob()

    advanceUntilIdle()

    val expected = viewModel.uiState.value.uiType
    val actual = HomeUiType.LOADED

    assertEquals(
      expected,
      actual
    )

    job.cancel()
  }

  @Test
  fun whenLoadedTypeIsSet_thenItemsIsAtLeastOne() = runTest {
    val job = baseSideEffectJob()

    advanceUntilIdle()
    val expected = viewModel.uiState.value.uiType
    val actual = HomeUiType.LOADED

    assertEquals(
      expected,
      actual
    )

    job.cancel()
  }

  @Test
  fun whenTriggerProductMark_thenChangeState() = runTest {
    val job = baseSideEffectJob()

    advanceUntilIdle()

    viewModel.uiState.value.homeUiModels.forEachIndexed { homeUiModelIndex, homeUiModel ->
      homeUiModel.productUiModels.forEachIndexed { productUiModelIndex, productUiModel ->
        val favoriteState = productUiModel.isFavorite

        viewModel.setEvent(
          HomeUiEvent.OnClickedUnmarkedFavorite(
            homeUiModel.section.id, productUiModel.id
          )
        )

        advanceUntilIdle()
        val updatedFavoriteState = viewModel.uiState.value
          .homeUiModels[homeUiModelIndex]
          .productUiModels[productUiModelIndex]
          .isFavorite

        val expected = true
        val actual = favoriteState != updatedFavoriteState

        assertEquals(
          expected,
          actual
        )
      }
    }
    job.cancel()
  }
}
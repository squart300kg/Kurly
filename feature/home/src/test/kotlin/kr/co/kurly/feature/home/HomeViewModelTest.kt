package kr.co.kurly.feature.home

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kr.co.kurly.test.testing.repository.TestProductRepository
import kr.co.kurly.test.testing.usecase.TestGetProductUseCase
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
  private val getProductUseCase = TestGetProductUseCase()

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
}
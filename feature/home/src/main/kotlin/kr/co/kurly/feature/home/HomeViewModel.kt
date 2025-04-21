package kr.co.kurly.feature.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.co.kurly.core.domain.GetProductsUseCase
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.ui.BaseViewModel
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.PriceUiModel
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel
import kr.co.kurly.core.ui.UiEvent
import kr.co.kurly.core.ui.UiSideEffect
import kr.co.kurly.core.ui.UiState
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val productRepository: ProductRepository,
  private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<HomeUiState, HomeUiEvent, HomeUiSideEffect>() {

  override fun createInitialState(): HomeUiState {
    return HomeUiState()
  }

  override fun handleEvent(event: HomeUiEvent) {
    when (event) {
      is HomeUiEvent.OnScrolledToEnd -> {
        setEffect { HomeUiSideEffect.Load.More(event.nextPage) }
      }
      is HomeUiEvent.OnClickedMarkedFavorite -> {
        viewModelScope.launch {
          runCatching {
            productRepository.unmarkFavorite(
              dtoRequest = FavoriteMakingDtoRequest(
                sectionId = event.sectionId,
                productId = event.productId
              )
            )
          }.onFailure(::setErrorState)
        }
      }
      is HomeUiEvent.OnClickedUnmarkedFavorite -> {
        viewModelScope.launch {
          runCatching {
            productRepository.markFavorite(
              dtoRequest = FavoriteMakingDtoRequest(
                sectionId = event.sectionId,
                productId = event.productId
              )
            )
          }.onFailure(::setErrorState)
        }
      }
      is HomeUiEvent.OnPullToRefresh -> {
        setEffect { HomeUiSideEffect.Load.Refresh }
      }
    }
  }

  init {
    setEffect { HomeUiSideEffect.Load.First }

    viewModelScope.launch {
      productRepository.observeAllFavoriteIds()
        .onEach {
          while(uiState.value.homeUiModels.isEmpty()) {
            delay(100L)
          }
        }
        .collect { dtoResponses ->
          val updatedHomeUiModels = uiState.value.homeUiModels.map { homeUiModel ->
            val updatedProductUiModels = homeUiModel.productUiModels.map { productUiModel ->
              val isFavorite = dtoResponses.any {
                  it.sectionId == homeUiModel.section.id &&
                  it.productId == productUiModel.id
              }
              productUiModel.copy(isFavorite = isFavorite)
            }.toPersistentList()

            homeUiModel.copy(productUiModels = updatedProductUiModels)
          }.toPersistentList()

          setState {
            copy(homeUiModels = updatedHomeUiModels)
          }
        }
    }
  }

  fun fetchData(loadType: HomeUiSideEffect.Load) {
    viewModelScope.launch {
      getProductsUseCase(
        page = when (loadType) {
          is HomeUiSideEffect.Load.First,
          is HomeUiSideEffect.Load.Refresh -> 1
          is HomeUiSideEffect.Load.More -> loadType.pageId
        }
      )
        .onStart {
          setState {
            if (loadType is HomeUiSideEffect.Load.Refresh) {
              copy(isRefresh = true)
            } else {
              copy(isLoading = true)
            }
          }
        }
        .onCompletion {
          setState {
            if (loadType is HomeUiSideEffect.Load.Refresh) {
              copy(isRefresh = false)
            } else {
              copy(isLoading = false)
            }
          }
        }
        .catch { setErrorState(it) }
        .collect {
          setState {
            copy(
              uiType = HomeUiType.LOADED,
              homeUiModels =
              when (loadType) {
                is HomeUiSideEffect.Load.First,
                is HomeUiSideEffect.Load.Refresh -> {
                  HomeUiModel.mapperToUiModel(it)
                }
                is HomeUiSideEffect.Load.More -> {
                  (homeUiModels as PersistentList)
                    .addAll(HomeUiModel.mapperToUiModel(it))
                }
              },
              nextPage = it.nextPage
            )
          }
        }
    }
  }
}
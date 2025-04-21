package kr.co.kurly.feature.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.co.kurly.core.domain.GetProductsUseCase
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.ui.BaseViewModel
import javax.inject.Inject

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
      is HomeUiEvent.OnPullToRefresh -> {
        setEffect { HomeUiSideEffect.Load.Refresh }
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
    }
  }

  private val cachedProductFavoriteIds = MutableSharedFlow<List<FavoriteMakingDtoResponse>>(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
  )

  init {
    setEffect { HomeUiSideEffect.Load.First }

    viewModelScope.launch {
      productRepository.observeAllFavoriteIds()
        .collect { dtoResponses ->
          cachedProductFavoriteIds.emit(dtoResponses)
          setState {
            val homeUiModels = homeUiModels.syncWithFavoriteIdsAndGet(dtoResponses)
            copy(
              uiType =
              if (homeUiModels.isNotEmpty()) HomeUiType.LOADED
              else HomeUiType.NONE,
              homeUiModels = homeUiModels
            )
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
        .collect { domainResponse ->
          val favoriteIds = cachedProductFavoriteIds.first()
          setState {
            copy(
              uiType = HomeUiType.LOADED,
              homeUiModels =
              when (loadType) {
                is HomeUiSideEffect.Load.First,
                is HomeUiSideEffect.Load.Refresh -> {
                  HomeUiModel.mapperToUiModel(domainResponse).syncWithFavoriteIdsAndGet(favoriteIds)
                }
                is HomeUiSideEffect.Load.More -> {
                  (homeUiModels as PersistentList)
                    .addAll(HomeUiModel.mapperToUiModel(domainResponse)).syncWithFavoriteIdsAndGet(favoriteIds)
                }
              },
              nextPage = domainResponse.nextPage
            )
          }
        }
    }
  }

  private fun ImmutableList<HomeUiModel>.syncWithFavoriteIdsAndGet(
    dtoResponses: List<FavoriteMakingDtoResponse>
  ): ImmutableList<HomeUiModel> {
    val updatedHomeUiModels = this.map { homeUiModel ->
      val updatedProductUiModels = homeUiModel.productUiModels.map { productUiModel ->
        val isFavorite = dtoResponses.any {
          it.sectionId == homeUiModel.section.id &&
            it.productId == productUiModel.id
        }
        productUiModel.copy(isFavorite = isFavorite)
      }.toPersistentList()

      homeUiModel.copy(productUiModels = updatedProductUiModels)
    }.toPersistentList()

    return updatedHomeUiModels
  }
}
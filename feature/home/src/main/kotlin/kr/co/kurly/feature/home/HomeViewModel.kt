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

enum class HomeUiType {
  NONE,
  LOADED
}

data class HomeUiModel(
  val section: SectionDtoResponse,
  val productUiModels: ImmutableList<ProductUiModel>
) {
  companion object {
    fun mapperToUiModel(domainResponse: SectionProductDomainResponse): ImmutableList<HomeUiModel> {
      return domainResponse.sectionProducts.map {
        HomeUiModel(
          section = it.section,
          productUiModels = it.products.map { product ->
            ProductUiModel(
              id = product.id,
              image = product.image,
              name = product.name,
              isFavorite = product.isFavorite,
              productSectionType =
              if (it.section.type == SectionType.VERTICAL) ProductSectionType.WIDTH_EXPANDED
              else ProductSectionType.NORMAL,
              priceUiModel = PriceUiModel(
                priceType = product.price,
                priceLineType =
                if (it.section.type == SectionType.VERTICAL) PriceLineType.ONE_LINE
                else PriceLineType.TWO_LINE
              )
            )
          }.toPersistentList()
        )
      }.toPersistentList()
    }
  }
}

data class HomeUiState(
  val uiType: HomeUiType = HomeUiType.NONE,
  val homeUiModels: ImmutableList<HomeUiModel> = persistentListOf(),
  val nextPage: Int? = null,
  val isLoading: Boolean = false
) : UiState

sealed interface HomeUiEvent : UiEvent {
  @JvmInline
  value class OnScrolledToEnd(val nextPage: Int) : HomeUiEvent
  data class OnClickedMarkedFavorite(val sectionId: Int, val productId: Int) : HomeUiEvent
  data class OnClickedUnmarkedFavorite(val sectionId: Int, val productId: Int) : HomeUiEvent

}

sealed interface HomeUiSideEffect : UiSideEffect {
  sealed interface Load : HomeUiSideEffect {
    data object First : Load
    data class More(val pageId: Int) : Load
  }
}

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
          is HomeUiSideEffect.Load.First -> 1
          is HomeUiSideEffect.Load.More -> loadType.pageId
        }
      )
        .onStart { }
        .onCompletion { }
        .catch { setErrorState(it) }
        .collect {
          setState {
            copy(
              uiType = HomeUiType.LOADED,
              homeUiModels =
              when (loadType) {
                is HomeUiSideEffect.Load.First -> {
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
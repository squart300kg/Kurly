package kr.co.kurly.feature.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.co.kurly.core.domain.GetProductsUseCase
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.ProductRepository
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

enum class HomeUiType {
  NONE,
  LOADED
}

data class HomeUiModel(
  val section: SectionDtoResponse,
  val productUiModels: ImmutableList<ProductUiModel>
) {
  companion object {
    fun mapperToUiModel(domainResponse: List<SectionProductDomainResponse>): ImmutableList<HomeUiModel> {
      return domainResponse.map {
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
          }.toImmutableList()
        )
      }
        .toImmutableList()
    }
  }
}

data class HomeUiState(
  val uiType: HomeUiType = HomeUiType.NONE,
  val homeUiModel: ImmutableList<HomeUiModel> = persistentListOf(),
  val isLoading: Boolean = false
) : UiState

sealed interface HomeUiEvent : UiEvent {

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
      else -> {}
    }
  }

  init {
    setEffect { HomeUiSideEffect.Load.First }
  }

  fun fetchData() {
    viewModelScope.launch {
      getProductsUseCase(page = 1)
        .onStart { }
        .onCompletion { }
        .catch { setErrorState(it) }
        .collect {
          setState {
            copy(
              uiType = HomeUiType.LOADED,
              homeUiModel = HomeUiModel.mapperToUiModel(it)
            )
          }
        }
    }
  }
}
package kr.co.kurly.feature.home

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.PriceUiModel
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel
import kr.co.kurly.core.ui.UiEvent
import kr.co.kurly.core.ui.UiSideEffect
import kr.co.kurly.core.ui.UiState

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
  val isLoading: Boolean = false,
  val isRefresh: Boolean = false,
) : UiState

sealed interface HomeUiEvent : UiEvent {
  @JvmInline
  value class OnScrolledToEnd(val nextPage: Int) : HomeUiEvent
  data object OnPullToRefresh : HomeUiEvent
  data class OnClickedMarkedFavorite(val sectionId: Int, val productId: Int) : HomeUiEvent
  data class OnClickedUnmarkedFavorite(val sectionId: Int, val productId: Int) : HomeUiEvent

}

sealed interface HomeUiSideEffect : UiSideEffect {
  sealed interface Load : HomeUiSideEffect {
    data object First : Load
    data object Refresh : Load
    data class More(val pageId: Int) : Load
  }
}
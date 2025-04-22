package kr.co.kurly.feature.home.model

import kotlinx.collections.immutable.persistentListOf
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.PriceUiModel
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel
import kr.co.kurly.feature.home.HomeUiModel
import kr.co.kurly.feature.home.HomeUiState
import kr.co.kurly.feature.home.HomeUiType

object HomeUiStateTestData {
  val PRODUCT_COUNT = 6
  val initState = HomeUiState()
  val loadedState = HomeUiState(
    uiType = HomeUiType.LOADED,
    homeUiModels = persistentListOf<HomeUiModel>()
      .addAll(
        List(10) { homeUiModelIndex ->
          val sectionTypes = listOf(SectionType.GRID, SectionType.VERTICAL, SectionType.HORIZONTAL)
          val sectionType = sectionTypes[homeUiModelIndex % sectionTypes.size]
          HomeUiModel(
            section = SectionDtoResponse(
              id = homeUiModelIndex,
              title = "${homeUiModelIndex}_타이틀",
              type = sectionType,
              url = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
            ),
            productUiModels = persistentListOf<ProductUiModel>()
              .addAll(
                List(PRODUCT_COUNT) {
                  val productUiModelIndex = it + PRODUCT_COUNT * homeUiModelIndex
                  ProductUiModel(
                    id = productUiModelIndex,
                    image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
                    name = "${productUiModelIndex}_맛있는 바나나",
                    isFavorite = productUiModelIndex % 2 == 0,
                    productSectionType =
                    if (sectionType == SectionType.VERTICAL) ProductSectionType.WIDTH_EXPANDED
                    else ProductSectionType.NORMAL,
                    priceUiModel = PriceUiModel(
                      priceType = PriceType.Original(10000),
                      priceLineType =
                      if (sectionType == SectionType.VERTICAL) PriceLineType.ONE_LINE
                      else PriceLineType.TWO_LINE
                    ),
                  )
                }
              )
          )
        }
      )
  )
}
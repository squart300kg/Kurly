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
  val initState = HomeUiState()
  val loadedState = HomeUiState(
    uiType = HomeUiType.LOADED,
    homeUiModels = persistentListOf<HomeUiModel>()
      .addAll(
        List(10) {
          val sectionTypes = listOf(SectionType.GRID, SectionType.VERTICAL, SectionType.HORIZONTAL)
          val sectionType = sectionTypes[it % sectionTypes.size]
          HomeUiModel(
            section = SectionDtoResponse(
              id = it,
              title = "${it}_타이틀",
              type = sectionType,
              url = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
            ),
            productUiModels = persistentListOf<ProductUiModel>()
              .addAll(
                List(6) {
                  ProductUiModel(
                    id = it,
                    image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
                    name = "${it}_맛있는 바나나",
                    isFavorite = it % 2 == 0,
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
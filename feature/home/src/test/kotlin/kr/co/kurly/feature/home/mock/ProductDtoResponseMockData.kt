package kr.co.kurly.feature.home.mock

import kr.co.kurly.core.model.Paging
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

private val PRODUCT_COUNT = 6

fun getSectionDtoResponse(page: Int) = CommonDtoResponse(
  data = List(10) { sectionIndex ->
    val sectionTypes = listOf(SectionType.GRID, SectionType.VERTICAL, SectionType.HORIZONTAL)
    val sectionType = sectionTypes[sectionIndex % sectionTypes.size]
    SectionDtoResponse(
      id = sectionIndex,
      title = "${sectionIndex}_타이틀",
      type = sectionType,
      url = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
    )
  },
  paging = Paging(1)
)
fun getSectionProductDtoResponse(sectionId: Int) = CommonDtoResponse(
  data = List(6) {
    val productIndex = it + PRODUCT_COUNT * sectionId
    SectionProductDtoResponse(
      id = productIndex,
      price = PriceType.Original(10000),
      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      isSoldOut = productIndex % 2 == 0,
      name = "${productIndex}_맛있는 바나나",
      isFavorite = productIndex % 2 == 0
    )
  }
)

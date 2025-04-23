package kr.co.kurly.core.repository.mock

import kr.co.kurly.core.model.Paging
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.network.model.CommonApiResponse
import kr.co.kurly.core.network.model.SectionApiResponse
import kr.co.kurly.core.network.model.SectionProductApiResponse

private val PRODUCT_COUNT = 6

fun getSectionApiResponse(page: Int) = CommonApiResponse(
  data = List(10) { sectionIndex ->
    val sectionTypes = listOf(SectionType.GRID, SectionType.VERTICAL, SectionType.HORIZONTAL)
    val sectionType = sectionTypes[sectionIndex % sectionTypes.size]
    SectionApiResponse(
      id = sectionIndex,
      title = "${sectionIndex}_타이틀",
      type = sectionType.value,
      url = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
    )
  },
  paging = Paging(1)
)

fun getSectionProductApiResponse(sectionId: Int) = CommonApiResponse(
  data = List(PRODUCT_COUNT) {
    val productIndex = it + PRODUCT_COUNT * sectionId
    SectionProductApiResponse(
      id = productIndex,
      discountedPrice = null,
      originalPrice = 10000,
      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      isSoldOut = productIndex % 2 == 0,
      name = "${productIndex}_맛있는 바나나",
    )
  }
)
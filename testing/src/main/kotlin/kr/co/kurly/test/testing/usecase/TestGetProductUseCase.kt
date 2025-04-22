package kr.co.kurly.test.testing.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kr.co.kurly.core.domain.GetProductsUseCase
import kr.co.kurly.core.domain.model.SectionProduct
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

class TestGetProductUseCase : GetProductsUseCase {
  private val PRODUCT_COUNT = 6
  override fun invoke(page: Int): Flow<SectionProductDomainResponse> {
    return flowOf(
      SectionProductDomainResponse(
        sectionProducts = List(10) { sectionIndex ->
          val sectionTypes = listOf(SectionType.GRID, SectionType.VERTICAL, SectionType.HORIZONTAL)
          val sectionType = sectionTypes[sectionIndex % sectionTypes.size]
          SectionProduct(
            section = SectionDtoResponse(
              id = sectionIndex,
              title = "${sectionIndex}_타이틀",
              type = sectionType,
              url = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
            ),
            products = List(PRODUCT_COUNT) {
              val productIndex = it + PRODUCT_COUNT * sectionIndex
              SectionProductDtoResponse(
                id = productIndex,
                price = PriceType.Original(10000),
                image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
                isSoldOut = productIndex % 2 == 0,
                name = "${productIndex}_맛있는 바나나",
                isFavorite = productIndex % 2 == 0
              )
            },
          )
        },
        nextPage = page + 1
      )
    )
  }
}
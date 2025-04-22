package kr.co.kurly.test.testing.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kr.co.kurly.core.model.Paging
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

class TestProductRepository : ProductRepository {
  private val PRODUCT_COUNT = 6

  override fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>> {
    return flowOf(
      CommonDtoResponse(
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
    )
  }

  override fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    return flowOf(
      CommonDtoResponse(
        data = List(6) {
          val productIndex = it + PRODUCT_COUNT * (sectionId - 1)
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
    )
  }

  override fun observeAllFavoriteIds(): Flow<List<FavoriteMakingDtoResponse>> {
    return flowOf(emptyList())
  }

  override suspend fun markFavorite(dtoRequest: FavoriteMakingDtoRequest) {

  }

  override suspend fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest) {

  }
}
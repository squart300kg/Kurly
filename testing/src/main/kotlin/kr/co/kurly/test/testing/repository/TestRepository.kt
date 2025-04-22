package kr.co.kurly.test.testing.repository

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

class TestProductRepository : ProductRepository {
  override fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>> {
    TODO("Not yet implemented")
  }

  override fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    TODO("Not yet implemented")
  }

  override fun observeAllFavoriteIds(): Flow<List<FavoriteMakingDtoResponse>> {
    TODO("Not yet implemented")
  }

  override suspend fun markFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    TODO("Not yet implemented")
  }

  override suspend fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    TODO("Not yet implemented")
  }


}
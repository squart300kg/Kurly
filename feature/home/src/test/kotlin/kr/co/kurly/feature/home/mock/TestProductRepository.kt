package kr.co.kurly.feature.home.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

class TestProductRepository : ProductRepository {

  private val cachedFavoriteMakingDtoResponses =
    MutableStateFlow<List<FavoriteMakingDtoResponse>>(emptyList())

  override fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>> {
    return flowOf(getSectionDtoResponse(page))
  }

  override fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    return flowOf(getSectionProductDtoResponse(sectionId))
  }

  override fun observeAllFavoriteIds(): Flow<List<FavoriteMakingDtoResponse>> {
    return cachedFavoriteMakingDtoResponses
  }

  override suspend fun markFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    cachedFavoriteMakingDtoResponses.update {
      it.toMutableList().apply {
        add(
          FavoriteMakingDtoResponse(
            sectionId = dtoRequest.sectionId,
            productId = dtoRequest.productId
          )
        )
      }
    }
  }

  override suspend fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    cachedFavoriteMakingDtoResponses.update {
      it.toMutableList().apply {
        remove(
          FavoriteMakingDtoResponse(
            sectionId = dtoRequest.sectionId,
            productId = dtoRequest.productId
          )
        )
      }
    }
  }
}
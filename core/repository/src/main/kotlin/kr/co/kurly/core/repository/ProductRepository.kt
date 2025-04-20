package kr.co.kurly.core.repository

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

interface ProductRepository {

  fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>>

  fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>>

  fun observeAllFavoriteIds(): Flow<List<FavoriteMakingDtoResponse>>

  suspend fun markFavorite(dtoRequest: FavoriteMakingDtoRequest)

  suspend fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest)

}
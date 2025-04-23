package kr.co.kurly.core.repository

import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kr.co.kurly.core.database.dao.FavoriteProductDao
import kr.co.kurly.core.network.ProductApiService
import kr.co.kurly.core.network.operator.ResponseBaseOperator
import kr.co.kurly.core.repository.di.IoDispatcher
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoResponse
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
  private val productApiService: ProductApiService,
  private val favoriteProductDao: FavoriteProductDao,
  @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : ProductRepository {
  override fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>> {
    return flow {
      productApiService.getSections(page = page)
        .suspendOperator(
          ResponseBaseOperator(
            onSuccess = { apiResponse ->
              emit(
                value = apiResponse.data
                  .map(SectionDtoResponse::mapperToDto)
                  .let { dtoResponse ->
                    CommonDtoResponse(
                      data = dtoResponse,
                      paging = apiResponse.paging
                    )
                  }
              )
            }
          )
        )
    }
  }

  override fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    return flow {
      productApiService.getSectionProduct(sectionId = sectionId)
        .suspendOperator(
          ResponseBaseOperator(
            onSuccess = {
              emit(
                value = it.data
                  .map(SectionProductDtoResponse::mapperToDto)
                  .let(::CommonDtoResponse)
              )
            }
          )
        )
    }
  }

  override fun observeAllFavoriteIds(): Flow<List<FavoriteMakingDtoResponse>> {
    return favoriteProductDao.observeAllFavoriteProducts().map { entities ->
      entities.map(FavoriteMakingDtoResponse::mapperToDto)
    }
  }

  override suspend fun markFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    withContext(ioDispatcher) {
      favoriteProductDao.insert(
        entity = FavoriteMakingDtoRequest.mapperToDto(dtoRequest)
      )
    }
  }

  override suspend fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    withContext(ioDispatcher) {
      favoriteProductDao.delete(
        sectionId = dtoRequest.sectionId,
        productId = dtoRequest.productId
      )
    }
  }
}



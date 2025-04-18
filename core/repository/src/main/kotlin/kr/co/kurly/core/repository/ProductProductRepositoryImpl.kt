package kr.co.kurly.core.repository

import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.kurly.core.network.ProductApiService
import kr.co.kurly.core.network.operator.ResponseBaseOperator
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class ProductProductRepositoryImpl @Inject constructor(
  private val productApiService: ProductApiService
) : ProductRepository {
  override fun getSections(page: Int): Flow<CommonDtoResponse<SectionDtoResponse>> {
    return flow {
      productApiService.getSections(page = page)
        .suspendOperator(
          ResponseBaseOperator(
            onSuccess = {
              emit(
                value = it.data
                  .map(SectionDtoResponse::mapperToDto)
                  .let(::CommonDtoResponse)
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

  override fun markFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    TODO("Not yet implemented")
  }

  override fun unmarkFavorite(dtoRequest: FavoriteMakingDtoRequest) {
    TODO("Not yet implemented")
  }
}



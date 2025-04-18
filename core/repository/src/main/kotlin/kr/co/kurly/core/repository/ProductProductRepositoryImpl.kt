package kr.co.kurly.core.repository

import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.kurly.core.network.ProductApiService
import kr.co.kurly.core.network.operator.ResponseBaseOperator
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class ProductProductRepositoryImpl @Inject constructor(
  private val productApiService: ProductApiService
) : ProductRepository {

  override fun getSectionProduct(sectionId: Int): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    return flow {
      productApiService.getSectionProduct(sectionId)
        .suspendOperator(
          ResponseBaseOperator(
            onSuccess = { it ->
              emit(it.data
                .map { SectionProductDtoResponse.mapperToDto(it) }
                .let(::CommonDtoResponse))
            }
          )
        )
    }
  }
}



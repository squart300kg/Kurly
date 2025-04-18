package kr.co.kurly.core.repository

import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.kurly.core.network.ProductApiService
import kr.co.kurly.core.network.operator.ResponseBaseOperator
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class ProductProductRepositoryImpl @Inject constructor(
  private val productApiService: ProductApiService
) : ProductRepository {

  override fun getList(): Flow<SectionProductDtoResponse> {
    return flow {
      productApiService.getSectionProduct(2)
        .suspendOperator(
          ResponseBaseOperator(
            onSuccess = { it ->

            }
          )
        )
    }
  }
}



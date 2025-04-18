package kr.co.kurly.core.domain

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class ObserveProductsUseCaseImpl @Inject constructor(
  private val productRepository: ProductRepository
): ObserveProductsUseCase {

  override fun invoke(): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    TODO("Not yet implemented")
  }
}
package kr.co.kurly.core.domain

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
  private val productRepository: ProductRepository
): GetProductsUseCase {

  /**
   * 1. getSections호출
   * 2. getSectionProduct호출 -> product받음
   * 3. favoriteIds받음
   * 4. product와 favorite조율해서 isFavorite필드 true 처리
   */
  override fun invoke(): Flow<CommonDtoResponse<SectionProductDtoResponse>> {
    productRepository.getSections()
    productRepository.getSectionProduct()
    productRepository.observeAllFavoriteIds()

  }
}
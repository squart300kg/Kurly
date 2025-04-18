package kr.co.kurly.core.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductsUseCaseImpl @Inject constructor(
  private val productRepository: ProductRepository
): GetProductsUseCase {
  override fun invoke(page: Int): Flow<List<SectionProductDomainResponse>> {
    return productRepository.getSections(page = page)
      .flatMapConcat {
        it.data.map { sectionDtoResponse ->
          SectionProductDomainResponse(
            section = sectionDtoResponse,
            products = productRepository.getSectionProduct(sectionDtoResponse.id).first().data
          )
        }.let(::flowOf)
      }
  }
}
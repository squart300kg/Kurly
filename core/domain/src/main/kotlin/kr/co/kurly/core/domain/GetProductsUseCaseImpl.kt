package kr.co.kurly.core.domain

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kr.co.kurly.core.domain.model.SectionProduct
import kr.co.kurly.core.domain.model.SectionProductDomainResponse
import kr.co.kurly.core.repository.ProductRepository
import java.util.Collections
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
  private val productRepository: ProductRepository
) : GetProductsUseCase {
  override fun invoke(page: Int): Flow<SectionProductDomainResponse> = channelFlow {
    val sectionDtoResponse = productRepository.getSections(page = page).first()
    val sectionProducts = Collections.synchronizedList(mutableListOf<SectionProduct>())
    val sectionProductsJobs = mutableListOf<Job>()
    sectionDtoResponse.data.forEach { section ->
      launch {
        SectionProduct(
          section = section,
          products = productRepository.getSectionProduct(section.id).first().data
        ).also(sectionProducts::add)
      }.also(sectionProductsJobs::add)
    }

    sectionProductsJobs.joinAll()

    send(
      element = SectionProductDomainResponse(
        sectionProducts = sectionProducts,
        nextPage = sectionDtoResponse.paging?.next_page
      )
    )
  }
}
package kr.co.kurly.core.domain

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.domain.model.SectionProductDomainResponse

interface GetProductsUseCase {

  operator fun invoke(page: Int): Flow<SectionProductDomainResponse>

}
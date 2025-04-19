package kr.co.kurly.core.domain.model

import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

data class SectionProductDomainResponse(
  val sectionProducts: List<SectionProduct>,
  val nextPage: Int?
)

data class SectionProduct(
  val section: SectionDtoResponse,
  val products: List<SectionProductDtoResponse>,
)

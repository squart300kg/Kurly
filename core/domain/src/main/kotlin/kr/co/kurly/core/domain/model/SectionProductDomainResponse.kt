package kr.co.kurly.core.domain.model

import kr.co.kurly.core.repository.dto.SectionDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

data class SectionProductDomainResponse(
  val section: SectionDtoResponse,
  val products: List<SectionProductDtoResponse>
)

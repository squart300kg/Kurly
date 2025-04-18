package kr.co.kurly.core.domain

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.dto.CommonDtoResponse
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

interface ObserveProductsUseCase {

  operator fun invoke(): Flow<CommonDtoResponse<SectionProductDtoResponse>>

}
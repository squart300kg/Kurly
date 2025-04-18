package kr.co.kurly.core.repository

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse

interface ProductRepository {

  fun getList(): Flow<SectionProductDtoResponse>

}
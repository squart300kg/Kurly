package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.model.Paging

data class  CommonDtoResponse<out T>(
  val data: List<T>,
  val paging: Paging? = null
)
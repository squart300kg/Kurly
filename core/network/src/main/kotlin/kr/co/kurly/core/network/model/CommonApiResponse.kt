package kr.co.kurly.core.network.model

import kr.co.kurly.core.model.Paging

data class  CommonApiResponse<out T>(
  val data: List<T>,
  val paging: Paging? = null
)
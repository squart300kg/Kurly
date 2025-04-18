package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.network.model.SectionApiResponse

data class SectionDtoResponse(
  val id: Int,
  val title: String,
  val type: String,
  val url: String
) {
  companion object {
    fun mapperToDto(apiResponse: SectionApiResponse) = SectionDtoResponse(
      id = apiResponse.id,
      title = apiResponse.title,
      type = apiResponse.type,
      url = apiResponse.url
    )
  }
}

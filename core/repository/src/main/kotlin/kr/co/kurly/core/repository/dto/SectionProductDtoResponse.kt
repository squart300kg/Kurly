package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.network.model.SectionProductApiResponse

data class SectionProductDtoResponse(
  val discountedPrice: Int,
  val id: Int,
  val image: String,
  val isSoldOut: Boolean,
  val name: String,
  val originalPrice: Int
) {
  companion object {
    fun mapperToDto(apiResponse: SectionProductApiResponse) = SectionProductDtoResponse(
      discountedPrice = apiResponse.discountedPrice,
      id = apiResponse.id,
      image = apiResponse.image,
      isSoldOut = apiResponse.isSoldOut,
      name = apiResponse.name,
      originalPrice = apiResponse.originalPrice
    )
  }
}
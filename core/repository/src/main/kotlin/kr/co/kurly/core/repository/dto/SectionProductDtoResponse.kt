package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.network.model.SectionProductApiResponse

data class SectionProductDtoResponse(
  val originalPrice: Int,
  val discountedPrice: Int?,
  val discountedRate: Int?,
  val id: Int,
  val image: String,
  val isSoldOut: Boolean,
  val name: String,
  val isFavorite: Boolean
) {
  companion object {
    fun mapperToDto(apiResponse: SectionProductApiResponse) = SectionProductDtoResponse(
      originalPrice = apiResponse.originalPrice,
      discountedPrice = apiResponse.discountedPrice,
      discountedRate = apiResponse.discountedPrice,
      id = apiResponse.id,
      image = apiResponse.image,
      isSoldOut = apiResponse.isSoldOut,
      name = apiResponse.name,
      isFavorite = false
    )
  }
}
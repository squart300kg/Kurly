package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.network.model.SectionProductApiResponse

data class SectionProductDtoResponse(
  val id: Int,
  val price: PriceType,
  val image: String,
  val isSoldOut: Boolean,
  val name: String,
  val isFavorite: Boolean
) {
  companion object {
    fun mapperToDto(apiResponse: SectionProductApiResponse) = SectionProductDtoResponse(
      price = apiResponse.discountedPrice?.let { discountedPrice ->
        PriceType.Discounted(
          originalPrice = apiResponse.originalPrice,
          discountedPrice = discountedPrice,
          discountedRate = PriceType.Discounted.calculateDiscountRate(
            originalPrice = apiResponse.originalPrice,
            discountedPrice = discountedPrice
          )
        )} ?: run { PriceType.Original(apiResponse.originalPrice) },
      id = apiResponse.id,
      image = apiResponse.image,
      isSoldOut = apiResponse.isSoldOut,
      name = apiResponse.name,
      isFavorite = false
    )

  }
}
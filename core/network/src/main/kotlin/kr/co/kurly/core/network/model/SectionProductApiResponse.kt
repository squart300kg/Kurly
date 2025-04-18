package kr.co.kurly.core.network.model

data class SectionProductApiResponse(
  val originalPrice: Int,
  val discountedPrice: Int?,
  val id: Int,
  val image: String,
  val isSoldOut: Boolean,
  val name: String
)
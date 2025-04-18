package kr.co.kurly.core.network.model

data class SectionProductApiResponse(
  val discountedPrice: Int,
  val id: Int,
  val image: String,
  val isSoldOut: Boolean,
  val name: String,
  val originalPrice: Int
)
package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.network.model.SectionProductApiResponse

data class SectionProductDtoResponse(
  val data: List<Data>
) {
//  companion object {
//    fun mapperToDto(apiResponse: SectionProductApiResponse) = SectionProductDtoResponse(
//      data = apiResponse.data.map {
//        Data(
//          discountedPrice = it.discountedPrice,
//          id = it.id,
//          image = it.image,
//          isSoldOut = it.isSoldOut,
//          name = it.name,
//          originalPrice = it.originalPrice
//        )
//      }
//    )
//  }
  data class Data(
    val discountedPrice: Int,
    val id: Int,
    val image: String,
    val isSoldOut: Boolean,
    val name: String,
    val originalPrice: Int
  )
}
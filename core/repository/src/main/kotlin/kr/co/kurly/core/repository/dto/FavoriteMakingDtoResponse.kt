package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.database.entity.FavoriteProductEntity

data class FavoriteMakingDtoResponse(
  val sectionId: Int,
  val productId: Int
) {
  companion object {
    fun mapperToDto(dtoRequest: FavoriteProductEntity): FavoriteMakingDtoResponse {
      return FavoriteMakingDtoResponse(
        sectionId = dtoRequest.sectionId,
        productId = dtoRequest.productId
      )
    }
  }
}
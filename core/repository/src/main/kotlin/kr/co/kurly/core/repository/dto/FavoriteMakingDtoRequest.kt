package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.database.entity.FavoriteProductEntity

data class FavoriteMakingDtoRequest(
  val sectionId: Int,
  val productId: Int
) {
  companion object {
    fun mapperToDto(dtoRequest: FavoriteMakingDtoRequest): FavoriteProductEntity {
      return FavoriteProductEntity(
        sectionId = dtoRequest.sectionId,
        productId = dtoRequest.productId
      )
    }
  }
}

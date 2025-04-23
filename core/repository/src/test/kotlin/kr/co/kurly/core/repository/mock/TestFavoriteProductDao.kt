package kr.co.kurly.core.repository.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kr.co.kurly.core.database.dao.FavoriteProductDao
import kr.co.kurly.core.database.entity.FavoriteProductEntity

class TestFavoriteProductDao: FavoriteProductDao {

  private val cachedProductFavoriteIds = MutableStateFlow<List<FavoriteProductEntity>>(emptyList())

  override fun observeAllFavoriteProducts(): Flow<List<FavoriteProductEntity>> {
    return cachedProductFavoriteIds
  }

  override fun insert(entity: FavoriteProductEntity) {
    cachedProductFavoriteIds.update {
      it.toMutableList().apply {
        add(entity)
      }
    }
  }

  override fun delete(sectionId: Int, productId: Int) {
    cachedProductFavoriteIds.update {
      it.toMutableList().apply {
        remove(
          FavoriteProductEntity(
            sectionId = sectionId,
            productId = productId
          )
        )
      }
    }
  }
}
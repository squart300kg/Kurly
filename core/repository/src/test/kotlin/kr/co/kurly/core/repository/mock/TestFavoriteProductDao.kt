package kr.co.kurly.core.repository.mock

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.database.dao.FavoriteProductDao
import kr.co.kurly.core.database.entity.FavoriteProductEntity

class TestFavoriteProductDao: FavoriteProductDao {
  override fun observeAllFavoriteProducts(): Flow<List<FavoriteProductEntity>> {
    TODO("Not yet implemented")
  }

  override fun insert(entity: FavoriteProductEntity) {
    TODO("Not yet implemented")
  }

  override fun delete(sectionId: Int, productId: Int) {
    TODO("Not yet implemented")
  }
}
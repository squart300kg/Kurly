package kr.co.kurly.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.database.entity.FavoriteProductEntity

@Dao
interface FavoriteProductDao {
  @Query("SELECT * FROM FavoriteProductEntity")
  fun observeAllFavoriteProducts(): Flow<List<FavoriteProductEntity>>

  @Insert
  fun insert(entity: FavoriteProductEntity)

  @Query("DELETE FROM FavoriteProductEntity WHERE sectionId = :sectionId AND productId = :productId")
  fun delete(sectionId: Int, productId: Int)
}
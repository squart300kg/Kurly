package kr.co.kurly.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.database.entity.BookmarkedProductEntity

@Dao
interface ProductDao {

  @Query("SELECT * FROM BookmarkedProductEntity")
  fun observeAllFavoriteProducts(): Flow<List<BookmarkedProductEntity>>

}
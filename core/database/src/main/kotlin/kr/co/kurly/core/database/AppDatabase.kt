package kr.co.kurly.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.co.kurly.core.database.dao.ProductDao
import kr.co.kurly.core.database.entity.BookmarkedProductEntity

@Database(
    entities = [BookmarkedProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
}

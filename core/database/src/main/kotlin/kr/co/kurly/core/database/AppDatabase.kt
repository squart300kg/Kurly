package kr.co.kurly.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.co.kurly.core.database.dao.FavoriteProductDao
import kr.co.kurly.core.database.entity.FavoriteProductEntity

@Database(
    entities = [FavoriteProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : FavoriteProductDao
}

package kr.co.kurly.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkedProductEntity(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  @ColumnInfo(name = "sectionId") val sectionId: Int,
  @ColumnInfo(name = "productId") val productId: Int,
)

package kr.co.kurly.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.kurly.core.database.AppDatabase
import kr.co.kurly.core.database.dao.ProductDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext appContext: Context
  ): AppDatabase {
    return Room.databaseBuilder(
      appContext,
      AppDatabase::class.java,
      "korean_investment.db"
    ).build()
  }

  @Provides
  @Singleton
  fun provideMarvelCharacterDao(database: AppDatabase): ProductDao {
    return database.productDao()
  }
}
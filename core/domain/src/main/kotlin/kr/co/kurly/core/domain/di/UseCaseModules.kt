package kr.co.kurly.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.kurly.core.domain.GetProductsUseCase
import kr.co.kurly.core.domain.GetProductsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModules {
  @Singleton
  @Binds
  fun bindGetProductsUseCase(
    usecase: GetProductsUseCaseImpl
  ): GetProductsUseCase
}
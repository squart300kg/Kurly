package kr.co.kurly.core.network.di

import android.content.Context
import android.util.Log
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.kurly.core.network.BuildConfig
import kr.co.kurly.core.network.FileProvider
import kr.co.kurly.core.network.AssetFileProvider
import kr.co.kurly.core.network.ProductService
import kr.co.kurly.core.network.interceptor.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideFileProvider(
    @ApplicationContext context: Context
  ): FileProvider =
    AssetFileProvider(context)

  @Singleton
  @Provides
  fun provideMockInterceptor(
    @ApplicationContext applicationContext: Context
  ): MockInterceptor =
    MockInterceptor(applicationContext)

  @Singleton
  @Provides
  fun provideDebugInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor { message ->
      Log.d("API", message)
    }.apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(
    mockInterceptor: MockInterceptor,
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(mockInterceptor)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideGsonConverter(): GsonConverterFactory {
    return GsonConverterFactory.create()
  }

  @Provides
  @Singleton
  fun provideMarbleCharacterApi(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
  ): ProductService {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.apiUrl)
      .client(okHttpClient)
      .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
      .addConverterFactory(gsonConverterFactory)
      .build()
      .create(ProductService::class.java)
  }
}
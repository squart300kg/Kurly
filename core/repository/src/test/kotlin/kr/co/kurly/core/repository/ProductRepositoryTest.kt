package kr.co.kurly.core.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kr.co.kurly.core.repository.mock.TestFavoriteProductDao
import kr.co.kurly.core.repository.mock.TestProductApiService
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryTest {

  private val testDispatcher = UnconfinedTestDispatcher()
  private val testScope = TestScope(testDispatcher)

  private lateinit var repository: ProductRepositoryImpl


  @Before
  fun setup() {
    repository = ProductRepositoryImpl(
      productApiService = TestProductApiService(),
      favoriteProductDao = TestFavoriteProductDao(),
      ioDispatcher = testDispatcher
    )
  }

}
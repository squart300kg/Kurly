package kr.co.kurly.core.repository

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kr.co.kurly.core.repository.dto.FavoriteMakingDtoRequest
import kr.co.kurly.core.repository.mock.TestFavoriteProductDao
import kr.co.kurly.core.repository.mock.TestProductApiService
import org.junit.Before
import org.junit.Test

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

  @Test
  fun whenTriggerProductMark_thenChangeState() = testScope.runTest {
    repository.markFavorite(
      dtoRequest = FavoriteMakingDtoRequest(
        sectionId = 99,
        productId = 99
      )
    )

    var (observedSectionId, observedProductId) = -1 to -1
    val favoriteIds = repository.observeAllFavoriteIds().first()
    if (favoriteIds.isNotEmpty()) {
      observedSectionId = favoriteIds[0].sectionId
      observedProductId = favoriteIds[0].productId
    }

    assertEquals(
      true,
        observedSectionId == 99 && observedProductId == 99
    )
  }
}
package kr.co.kurly.feature.home

import kotlinx.coroutines.test.StandardTestDispatcher
import kr.co.kurly.test.testing.repository.TestProductRepository
import kr.co.kurly.test.testing.usecase.TestGetProductUseCase
import kr.co.kurly.test.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule

class HomeViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

  private lateinit var viewModel: HomeViewModel
  private val productRepository = TestProductRepository()
  private val getProductUseCase = TestGetProductUseCase()

  @Before
  fun setup() {
    viewModel = HomeViewModel(
      productRepository = productRepository,
      getProductsUseCase = getProductUseCase
    )
  }

}
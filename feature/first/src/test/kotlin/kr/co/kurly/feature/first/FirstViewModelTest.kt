package kr.co.kurly.feature.first

import kr.co.kurly.testing.TestRepository
import kr.co.kurly.test.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule

class FirstViewModelTest {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: FirstViewModel

  private val newsRepository = TestRepository()

  @Before
  fun setup() {
    viewModel = FirstViewModel(
      productRepository = newsRepository
    )
  }

}
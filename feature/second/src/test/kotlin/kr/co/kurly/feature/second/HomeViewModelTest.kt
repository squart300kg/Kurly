package kr.co.kurly.feature.second

import kr.co.kurly.feature.home.HomeViewModel
import kr.co.kurly.testing.TestRepository
import kr.co.kurly.test.testing.util.MainDispatcherRule
import org.junit.Before
import org.junit.Rule

class HomeViewModelTest {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var viewModel: HomeViewModel

  private val newsRepository = TestRepository()

  @Before
  fun setup() {
    viewModel = HomeViewModel(
      repository = newsRepository
    )
  }

}
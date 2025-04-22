package kr.co.kurly.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import kr.co.kurly.feature.home.model.HomeUiStateTestData
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun whenFirstUiLoaded_thenShowLoadedTypeUi() {
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateTestData.initState
      )
    }
  }

}
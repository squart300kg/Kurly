package kr.co.kurly.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import kr.co.kurly.feature.home.model.HomeUiStateTestData
import kr.co.kurly.test.testing.ui.TestTag.HORIZONTAL_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_LIST
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun whenFirstUiLoading_thenShowNoneTypeUi() {
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateTestData.initState
      )
    }
    composeTestRule
      .onNodeWithTag(PRODUCT_LIST)
      .assertDoesNotExist()
  }

}
package kr.co.kurly.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import junit.framework.TestCase.assertEquals
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.feature.home.preview.HomeUiStateMockData
import kr.co.kurly.test.testing.ui.TestTag.HORIZONTAL_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_LIST
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_MARK_ICON
import kr.co.kurly.test.testing.ui.TestTag.VERTICAL_ITEMS
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun whenFirstUiLoading_thenShowNoneTypeUi() {
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateMockData.initState
      )
    }
    composeTestRule
      .onNodeWithTag(PRODUCT_LIST)
      .assertDoesNotExist()
  }

  @Test
  fun whenFirstUiLoaded_thenShowLoadedTypeUi() {
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateMockData.loadedState
      )
    }
    composeTestRule
      .onNodeWithTag(PRODUCT_LIST)
      .assertExists()
  }

  @Test
  fun whenClickedProductItem_thenChangeMarkedState() {
    val updatedMarkedProducts = mutableListOf<Int>()
    val updatedUnmarkedProducts = mutableListOf<Int>()
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateMockData.loadedState,
        onClickedMarkedFavorite = { sectionId, productId ->
          updatedUnmarkedProducts.add(productId)
        },
        onClickedUnmarkedFavorite = { sectionId, productId ->
          updatedMarkedProducts.add(productId)
        }
      )
    }
    HomeUiStateMockData.loadedState.homeUiModels.forEachIndexed { homeUiModelIndex, homeUiModel ->
      composeTestRule
        .onNode(hasTestTag(PRODUCT_LIST))
        .performScrollToIndex(homeUiModelIndex)

      homeUiModel.productUiModels.forEach { productUiModel ->
        when (homeUiModel.section.type) {
          SectionType.NONE,
          SectionType.GRID -> { }
          SectionType.VERTICAL -> {
            composeTestRule
              .onNode(hasTestTag("${homeUiModel.section.id}_${VERTICAL_ITEMS}"))
              .performScrollToNode(
                hasTestTag("${productUiModel.id}_${PRODUCT_MARK_ICON}")
              )
          }
          SectionType.HORIZONTAL -> {
            composeTestRule
              .onNode(hasTestTag("${homeUiModel.section.id}_${HORIZONTAL_ITEMS}"))
              .performScrollToNode(
                hasTestTag("${productUiModel.id}_${PRODUCT_MARK_ICON}")
              )
          }
        }
        composeTestRule
          .onNode(hasTestTag("${productUiModel.id}_${PRODUCT_MARK_ICON}"))
          .performClick()
      }
    }

    val markedProducts = HomeUiStateMockData.loadedState.homeUiModels
      .map { homeUiModel ->
        homeUiModel.productUiModels
          .filter { it.isFavorite }
          .map { it.id }
      }
      .flatten()

    val unmarkedProducts = HomeUiStateMockData.loadedState.homeUiModels
        .map { homeUiModel ->
          homeUiModel.productUiModels
            .filter { !it.isFavorite }
            .map { it.id }
        }
        .flatten()

    assertEquals(
      updatedMarkedProducts,
      unmarkedProducts
    )
    assertEquals(
      updatedUnmarkedProducts,
      markedProducts
    )
  }

  @Test
  fun whenScrolledToEnd_thenLoadNextPage() {
    var nextPage: Int? = null
    composeTestRule.setContent {
      HomeScreen(
        uiState = HomeUiStateMockData.loadedState,
        onScrollToEnd = { nextPage = it }
      )
    }

    val lastProductId = HomeUiStateMockData.loadedState.homeUiModels
      .flatMap { it.productUiModels }
      .last()
      .id

    composeTestRule
      .onNodeWithTag(PRODUCT_LIST) // LazyColumn
      .performScrollToNode(
        hasTestTag("${lastProductId}_$PRODUCT_MARK_ICON")
      )

    assert(nextPage == HomeUiStateMockData.loadedState.nextPage)
  }
}
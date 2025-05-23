package kr.co.kurly.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.ui.BasePullToRefresh
import kr.co.kurly.core.ui.BaseSurface
import kr.co.kurly.core.ui.CommonHorizontalDivider
import kr.co.kurly.core.ui.ProductSection
import kr.co.kurly.core.ui.model.LocalOnErrorMessageChanged
import kr.co.kurly.core.ui.model.LocalOnLoadingStateChanged
import kr.co.kurly.core.ui.util.PaginationLoadEffect
import kr.co.kurly.feature.home.preview.HomeUiStatePreviewParam
import kr.co.kurly.test.testing.ui.TestTag.GRID_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.HORIZONTAL_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_LIST
import kr.co.kurly.test.testing.ui.TestTag.VERTICAL_ITEMS

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  LaunchedEffect(Unit) {
    viewModel.uiSideEffect.collect { effect ->
      when (effect) {
        is HomeUiSideEffect.Load -> viewModel.fetchData(effect)
      }
    }
  }

  HomeScreen(
    uiState = uiState,
    modifier = modifier,
    onClickedMarkedFavorite = { sectionId, productId ->
      viewModel.setEvent(HomeUiEvent.OnClickedMarkedFavorite(sectionId, productId))
    },
    onClickedUnmarkedFavorite = { sectionId, productId ->
      viewModel.setEvent(HomeUiEvent.OnClickedUnmarkedFavorite(sectionId, productId))
    },
    onPullToRefresh = {
      viewModel.setEvent(HomeUiEvent.OnPullToRefresh)
    },
    onScrollToEnd = {
      viewModel.setEvent(HomeUiEvent.OnScrolledToEnd)
    }
  )

  LocalOnLoadingStateChanged.current(uiState.isLoading)

  val localOnErrorMessageChanged by rememberUpdatedState(LocalOnErrorMessageChanged.current)
  LaunchedEffect(Unit) {
    viewModel.errorMessageState.collect {
      localOnErrorMessageChanged(it)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  uiState: HomeUiState,
  onScrollToEnd: () -> Unit = {},
  onClickedMarkedFavorite: (sectionId: Int, productId: Int) -> Unit = { _, _ -> },
  onClickedUnmarkedFavorite: (sectionId: Int, productId: Int) -> Unit = { _, _ -> },
  onPullToRefresh: () -> Unit = {},
  configuration: Configuration = LocalConfiguration.current
) {
  val listState = rememberLazyListState()
  PaginationLoadEffect(
    listState = listState,
    onScrollToEnd = onScrollToEnd
  )

  val pullToRefreshState = rememberPullToRefreshState()
  when (uiState.uiType) {
    HomeUiType.NONE -> {}
    HomeUiType.LOADED -> {
      LazyColumn(
        modifier = modifier
          .testTag(PRODUCT_LIST)
          .pullToRefresh(
            state = pullToRefreshState,
            isRefreshing = uiState.isRefresh,
            onRefresh = onPullToRefresh
          ),
        state = listState
      ) {
        items(
          items = uiState.homeUiModels,
          key = { homeUiModel -> homeUiModel.section.id },
          contentType = { homeUiModel -> homeUiModel.section.type }
        ) { homeUiModel ->
          when (homeUiModel.section.type) {
            SectionType.HORIZONTAL -> {
              Text(
                modifier = Modifier,
                text = homeUiModel.section.title,
                fontSize = 18.sp
              )
              Row(
                modifier = Modifier
                  .testTag("${homeUiModel.section.id}_${HORIZONTAL_ITEMS}")
                  .fillMaxWidth()
                  .horizontalScroll(rememberScrollState())
              ) {
                homeUiModel.productUiModels.forEachIndexed { index, productUiModel ->
                  key(productUiModel.id) {
                    ProductSection(
                      modifier = Modifier
                        .width((configuration.screenWidthDp / 2).dp),
                      productUiModel = productUiModel,
                      onClickedMarkedFavorite = {
                        onClickedMarkedFavorite(
                          homeUiModel.section.id,
                          productUiModel.id
                        )
                      },
                      onClickedUnmarkedFavorite = {
                        onClickedUnmarkedFavorite(
                          homeUiModel.section.id,
                          productUiModel.id
                        )
                      }
                    )
                  }
                }
              }
              CommonHorizontalDivider(
                verticalSpacerDp = 20.dp
              )
            }
            SectionType.GRID -> {
              Text(
                modifier = Modifier,
                text = homeUiModel.section.title,
                fontSize = 18.sp
              )
              Column(
                modifier = Modifier
                  .testTag("${homeUiModel.section.id}_${GRID_ITEMS}")
              ) {
                homeUiModel.productUiModels.chunked(3).forEach { products ->
                  Row {
                    products.forEach { productUiModel ->
                      key(productUiModel.id) {
                        ProductSection(
                          modifier = Modifier.weight(1f),
                          productUiModel = productUiModel,
                          onClickedMarkedFavorite = {
                            onClickedMarkedFavorite(
                              homeUiModel.section.id,
                              productUiModel.id
                            )
                          },
                          onClickedUnmarkedFavorite = {
                            onClickedUnmarkedFavorite(
                              homeUiModel.section.id,
                              productUiModel.id
                            )
                          }
                        )
                      }
                    }
                  }
                }
              }
              CommonHorizontalDivider(
                verticalSpacerDp = 20.dp
              )
            }
            SectionType.VERTICAL -> {
              Text(
                modifier = Modifier,
                text = homeUiModel.section.title,
                fontSize = 18.sp
              )
              Column(
                modifier = Modifier
                  .testTag("${homeUiModel.section.id}_${VERTICAL_ITEMS}")
              ) {
                homeUiModel.productUiModels.forEach { productUiModel ->
                  key(productUiModel.id) {
                    ProductSection(
                      modifier = Modifier.fillMaxWidth(),
                      productUiModel = productUiModel,
                      onClickedMarkedFavorite = {
                        onClickedMarkedFavorite(
                          homeUiModel.section.id,
                          productUiModel.id
                        )
                      },
                      onClickedUnmarkedFavorite = {
                        onClickedUnmarkedFavorite(
                          homeUiModel.section.id,
                          productUiModel.id
                        )
                      }
                    )
                  }
                }
              }
              CommonHorizontalDivider(
                verticalSpacerDp = 20.dp
              )
            }
            SectionType.NONE -> {}
          }
        }
      }
    }
  }

  BasePullToRefresh(
    modifier = modifier,
    pullToRefreshState = pullToRefreshState,
    isRefresh = uiState.isRefresh,
    onPullToRefresh = onPullToRefresh
  )
}

@Preview
@Composable
fun HomeScreenPreview(
  @PreviewParameter(HomeUiStatePreviewParam::class)
  homeUiStatePreviewParam: HomeUiState
) {
  BaseSurface {
    HomeScreen(
      uiState = homeUiStatePreviewParam
    )

  }
}
package kr.co.kurly.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.kurly.core.model.SectionType

const val HOME_BASE_ROUTE = "homeBaseRoute"
fun NavGraphBuilder.homeScreen() {
  composable(
    route = HOME_BASE_ROUTE
  ) {
    HomeScreen()
  }
}

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  LaunchedEffect(Unit) {
    viewModel.uiSideEffect.collect { effect ->
      when (effect) {
        is HomeUiSideEffect.Load -> viewModel.fetchData()
      }
    }
  }

  HomeScreen(
    uiState = uiState,
    modifier = modifier,
  )

}

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  uiState: HomeUiState,
) {

  when (uiState.uiType) {
    HomeUiType.NONE -> {}
    HomeUiType.LOADED -> {
      LazyColumn(modifier) {
        items(
          items = uiState.homeUiModel,
          key = { it.section.id}
        ) { homeUiModel ->
          when (homeUiModel.section.type) {
            SectionType.HORIZONTAL -> {
              Row(
                modifier = Modifier
              ) {
//                Image(
//                  painter = ,
//                  contentDescription = null,
//                )
              }
            }
            SectionType.GRID -> {}
            SectionType.VERTICAL -> {}
            SectionType.NONE -> {}
          }

        }
      }
    }
  }
}
package kr.co.kurly.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import kr.co.architecture.feature.home.R
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.ui.PriceSection

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
          key = { it.section.id }
        ) { homeUiModel ->
          when (homeUiModel.section.type) {
            SectionType.HORIZONTAL -> {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .horizontalScroll(rememberScrollState()),
              ) {
                homeUiModel.products.forEach { product ->
                  Column {
                    Image(
                      modifier = Modifier
                        .width(dimensionResource(R.dimen.product_width))
                        .height(dimensionResource(R.dimen.product_height))
                        .align(Alignment.CenterHorizontally),
                      painter = rememberAsyncImagePainter(
                        model = product.image
                      ),
                      contentScale = ContentScale.Fit,
                      contentDescription = null,
                    )

                    Text(
                      text = product.name,
                      maxLines = 2,
                      overflow = TextOverflow.Ellipsis,
                    )

                    PriceSection(
                      price = product.price
                    )
                  }
                }
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
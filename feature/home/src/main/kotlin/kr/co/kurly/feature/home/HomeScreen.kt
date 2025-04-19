package kr.co.kurly.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.co.kurly.core.model.SectionType
import kr.co.kurly.core.ui.CommonHorizontalDivider
import kr.co.kurly.core.ui.ProductSection

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
  configuration: Configuration = LocalConfiguration.current
) {
  when (uiState.uiType) {
    HomeUiType.NONE -> {}
    HomeUiType.LOADED -> {
      LazyColumn(modifier) {
        uiState.homeUiModel.forEach { homeUiModel ->
          when (homeUiModel.section.type) {
            SectionType.HORIZONTAL -> {
              item {
                key(homeUiModel.section.id) {
                  Text(
                    modifier = Modifier,
                    text = homeUiModel.section.title,
                    fontSize = 18.sp
                  )
                  Row(
                    modifier = Modifier
                      .fillMaxWidth()
                      .horizontalScroll(rememberScrollState())
                  ) {
                    homeUiModel.productUiModels.forEachIndexed { index, productUiModel ->
                      key(productUiModel.id) {
                        ProductSection(
                          modifier = Modifier
                            .width((configuration.screenWidthDp / 2).dp),
                          productUiModel = productUiModel
                        )
                      }
                    }
                  }
                  CommonHorizontalDivider(
                    verticalSpacerDp = 20.dp
                  )
                }
              }
            }
            SectionType.GRID -> {
              item {
                key(homeUiModel.section.id) {
                  Text(
                    modifier = Modifier,
                    text = homeUiModel.section.title,
                    fontSize = 18.sp
                  )
                  Column {
                    homeUiModel.productUiModels.chunked(3).forEach { products ->
                      Row {
                        products.forEach { productUiModel ->
                          key(productUiModel.id) {
                            ProductSection(
                              modifier = Modifier.weight(1f),
                              productUiModel = productUiModel
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
              }
            }
            SectionType.VERTICAL -> {
              item {
                key(homeUiModel.section.id) {
                  Text(
                    modifier = Modifier,
                    text = homeUiModel.section.title,
                    fontSize = 18.sp
                  )
                  Column {
                    homeUiModel.productUiModels.forEach { productUiModel ->
                      key(productUiModel.id) {
                        ProductSection(
                          modifier = Modifier.fillMaxWidth(),
                          productUiModel = productUiModel
                        )
                      }
                    }
                  }
                  CommonHorizontalDivider(
                    verticalSpacerDp = 20.dp
                  )
                }
              }
            }
            SectionType.NONE -> {}
          }
        }


























//        items(
//          items = uiState.homeUiModel,
//          key = { it.section.id }
//        ) { homeUiModel ->
//          when (homeUiModel.section.type) {
//            SectionType.HORIZONTAL -> {
//              Text(
//                modifier = Modifier,
//                text = homeUiModel.section.title,
//                fontSize = 18.sp
//              )
//              LazyRow(
//                modifier = Modifier.height(350.dp)
//              ) {
//                items(
//                  items = homeUiModel.products,
//                  key = { it.id }
//                ) { product ->
//                  Row {
//                    ProductSection(
//                      modifier = Modifier,
//                      product = product
//                    )
//                  }
//                }
//              }
//            }
//            SectionType.GRID -> {
//              Text(
//                modifier = Modifier,
//                text = homeUiModel.section.title,
//                fontSize = 18.sp
//              )
//              LazyHorizontalGrid(
//                modifier = Modifier
////                  .height((350 * 3).dp)
//                ,
//                rows = GridCells.Fixed(2)
//              ) {
//                items(
//                  items = homeUiModel.products,
//                  key = { it.id }
//                ) { product ->
//                  ProductSection(
//                    modifier = Modifier
//                      .width((configuration.screenWidthDp / 3).dp),
//                    product = product
//                  )
//                }
//              }
//            }
//            SectionType.VERTICAL -> {
//              Text(
//                modifier = Modifier,
//                text = homeUiModel.section.title,
//                fontSize = 18.sp
//              )
//              LazyColumn(
//                modifier = Modifier.height(350.dp)
//              ) {
//                items(
//                  items = homeUiModel.products,
//                  key = { it.id }
//                ) { product ->
//                  ProductSection(
//                    product = product
//                  )
//                }
//              }
//            }
//            SectionType.NONE -> {}
//          }
//        }















//        uiState.homeUiModel.forEach { homeUiModel ->
//          when (homeUiModel.section.type) {
//            SectionType.HORIZONTAL -> {
//              item {
//                Text(
//                  modifier = Modifier,
//                  text = homeUiModel.section.title,
//                  fontSize = 18.sp
//                )
//                LazyRow(
//                  modifier = Modifier.height(350.dp)
//                ) {
//                  items(
//                    items = homeUiModel.products,
//                    key = { it.id }
//                  ) { product ->
//                    Row {
//                      ProductSection(
//                        modifier = Modifier,
//                        product = product
//                      )
//                    }
//                  }
//                }
//              }
//            }
//            SectionType.GRID -> {
//              item {
//                Text(
//                  modifier = Modifier,
//                  text = homeUiModel.section.title,
//                  fontSize = 18.sp
//                )
//                LazyVerticalGrid(
//                  modifier = Modifier.height((350 * 2).dp),
//                  columns = GridCells.Fixed(2)
//                ) {
//                  items(
//                    items = homeUiModel.products,
//                    key = { it.id }
//                  ) { product ->
//                    ProductSection(
//                      product = product
//                    )
//                  }
//                }
//              }
//            }
//            SectionType.VERTICAL -> {
//              item {
//                Text(
//                  modifier = Modifier,
//                  text = homeUiModel.section.title,
//                  fontSize = 18.sp
//                )
//                LazyColumn(
//                  modifier = Modifier.height(350.dp)
//                ) {
//                  items(
//                    items = homeUiModel.products,
//                    key = { it.id }
//                  ) { product ->
//                    ProductSection(
//                      product = product
//                    )
//                  }
//                }
//              }
//            }
//            SectionType.NONE -> {}
//          }
//        }
      }
    }
  }
}
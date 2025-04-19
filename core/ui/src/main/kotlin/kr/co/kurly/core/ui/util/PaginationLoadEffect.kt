package kr.co.kurly.core.ui.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun PaginationLoadEffect(
    listState: LazyListState,
    nextPage: Int?,
    onScrollToEnd: (nextPage: Int) -> Unit
) {
  with(listState.layoutInfo) {
    if (visibleItemsInfo.lastOrNull() != null && totalItemsCount != 0) {
      val isScrolledToTheEnd by remember(this) {
        derivedStateOf {
          visibleItemsInfo.lastOrNull()?.index == totalItemsCount - 1
        }
      }

      LaunchedEffect(isScrolledToTheEnd, nextPage) {
        if (isScrolledToTheEnd && nextPage != null) {
          onScrollToEnd(nextPage)
        }
      }
    }
  }
}
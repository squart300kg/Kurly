package kr.co.kurly.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasePullToRefresh(
  modifier: Modifier,
  pullToRefreshState: PullToRefreshState,
  isRefresh: Boolean,
  onPullToRefresh: () -> Unit = {}
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
  ) {
    PullToRefreshBox(
      modifier = Modifier
        .align(Alignment.Center),
      isRefreshing = isRefresh,
      onRefresh = onPullToRefresh,
      state = pullToRefreshState
    ) { }
  }
}
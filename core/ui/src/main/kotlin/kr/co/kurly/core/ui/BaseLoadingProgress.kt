
package kr.co.kurly.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BaseLoadingProgress(
  modifier: Modifier = Modifier,
  isLoading: Boolean
) {
  if (isLoading) {
    Box(
      modifier = modifier
        .fillMaxSize()
    ) {
      CircularProgressIndicator(
        modifier = Modifier
          .align(Alignment.Center),
        color = Color.Gray
      )
    }
  }
}
package kr.co.kurly.core.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BaseSurface(
  content: @Composable () -> Unit
) {
  Surface(
    color = Color.White,
    content = content
  )
}
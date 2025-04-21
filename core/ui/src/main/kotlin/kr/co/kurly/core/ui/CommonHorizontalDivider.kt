package kr.co.kurly.core.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CommonHorizontalDivider(
  verticalSpacerDp: Dp = 0.dp
) {
  HorizontalDivider(
    modifier = Modifier
      .padding(vertical = verticalSpacerDp)
      .height(dimensionResource(R.dimen.divider_height)),
    color = Color.Gray
  )
}
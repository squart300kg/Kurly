package kr.co.kurly.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kr.co.architecture.core.ui.R
import kr.co.kurly.core.model.PriceType

@Composable
fun PriceSection(price: PriceType) {
  Row {
    when (price) {
      is PriceType.Discounted -> {
        Text(
          text = "${price.discountedRate}%",
          color = colorResource(R.color.productDiscount),
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "${price.discountedPrice}${stringResource(R.string.won)}",
          fontWeight = FontWeight.Bold
        )
      }
      is PriceType.Original -> {
        Text(
          text = "${price.price}${stringResource(R.string.won)}",
          fontWeight = FontWeight.Bold
        )
      }
    }
  }

  Column {
    when (val price = price) {
      is PriceType.Discounted -> {
        Text(
          text = "${price.discountedPrice}원",
          style = TextStyle(
            textDecoration = TextDecoration.LineThrough
          )
        )
      }
      is PriceType.Original -> {}
    }
  }
}
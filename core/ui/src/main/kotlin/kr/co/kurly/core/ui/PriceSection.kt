package kr.co.kurly.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kr.co.architecture.core.ui.R
import kr.co.kurly.core.model.PriceType

enum class PriceLineType {
  ONE_LINE,
  TWO_LINE
}

@Composable
fun PriceSection(
  modifier: Modifier = Modifier,
  priceType: PriceType,
  priceLineType: PriceLineType
) {
  Column(modifier) {
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      when (priceType) {
        is PriceType.Discounted -> {
          DiscountedRate(priceType.discountedRate)
          Price(priceType.discountedPrice)
          if (priceLineType == PriceLineType.ONE_LINE) {
            DiscountedPrice(priceType.originalPrice)
          }
        }
        is PriceType.Original -> {
          Price(priceType.price)
        }
      }
    }
    when (priceType) {
      is PriceType.Discounted -> {
        if (priceLineType == PriceLineType.TWO_LINE) {
          DiscountedPrice(priceType.originalPrice)
        }
      }
      is PriceType.Original -> {}
    }
  }
}

@Composable
private fun DiscountedRate(rate: Int) {
  Text(
    text = "${rate}%",
    color = colorResource(R.color.productDiscount),
    fontWeight = FontWeight.Bold
  )
}

@Composable
private fun DiscountedPrice(price: Int) {
  Text(
    text = "${price}${stringResource(R.string.won)}",
    style = TextStyle(
      textDecoration = TextDecoration.LineThrough
    )
  )
}

@Composable
private fun Price(price: Int) {
  Text(
    text = "${price}${stringResource(R.string.won)}",
    fontWeight = FontWeight.Bold
  )
}
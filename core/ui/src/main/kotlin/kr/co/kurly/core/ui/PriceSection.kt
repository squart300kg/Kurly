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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.co.architecture.core.ui.R
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.ui.preview.PriceUiModelPreviewParam
import kr.co.kurly.core.ui.preview.ProductUiModelPreviewParam

data class PriceUiModel(
  val priceType: PriceType,
  val priceLineType: PriceLineType
)

enum class PriceLineType {
  ONE_LINE,
  TWO_LINE
}

@Composable
fun PriceSection(
  modifier: Modifier = Modifier,
  priceUiModel: PriceUiModel
) {
  Column(modifier) {
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      when (priceUiModel.priceType) {
        is PriceType.Discounted -> {
          DiscountedRate(priceUiModel.priceType.discountedRate)
          MainPrice(priceUiModel.priceType.discountedPrice)
          if (priceUiModel.priceLineType == PriceLineType.ONE_LINE) {
            BeforeDiscountPrice(priceUiModel.priceType.originalPrice)
          }
        }
        is PriceType.Original -> {
          MainPrice(priceUiModel.priceType.price)
        }
      }
    }
    when (priceUiModel.priceType) {
      is PriceType.Discounted -> {
        if (priceUiModel.priceLineType == PriceLineType.TWO_LINE) {
          BeforeDiscountPrice(priceUiModel.priceType.originalPrice)
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
private fun BeforeDiscountPrice(price: Int) {
  Text(
    text = "${price}${stringResource(R.string.won)}",
    style = TextStyle(
      textDecoration = TextDecoration.LineThrough
    )
  )
}

@Composable
private fun MainPrice(price: Int) {
  Text(
    text = "${price}${stringResource(R.string.won)}",
    fontWeight = FontWeight.Bold
  )
}

@Preview
@Composable
fun PriceSectionPreview(
  @PreviewParameter(PriceUiModelPreviewParam::class)
  priceUiModel: PriceUiModel
) {
  BaseSurface {
    PriceSection(
      priceUiModel = priceUiModel
    )
  }
}
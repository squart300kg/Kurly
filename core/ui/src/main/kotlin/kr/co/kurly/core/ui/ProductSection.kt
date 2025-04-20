package kr.co.kurly.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.rememberAsyncImagePainter
import kr.co.architecture.core.ui.R
import kr.co.kurly.core.ui.preview.ProductUiModelPreviewParam

data class ProductUiModel(
  val id: Int,
  val image: String,
  val name: String,
  val isFavorite: Boolean,
  val productSectionType: ProductSectionType,
  val priceUiModel: PriceUiModel
)

enum class ProductSectionType {
  NORMAL,
  WIDTH_EXPANDED
}

@Composable
fun ProductSection(
  modifier: Modifier = Modifier,
  productUiModel: ProductUiModel,
  onClickedMarkedFavorite: () -> Unit = {},
  onClickedUnmarkedFavorite: () -> Unit = {}
) {
  Column(modifier) {
    Box(
      modifier = Modifier
        .align(Alignment.CenterHorizontally),
    ) {
      val imageModifier by rememberUpdatedState(
        when (productUiModel.productSectionType) {
          ProductSectionType.NORMAL -> Modifier
            .widthIn(max = dimensionResource(R.dimen.product_width))
            .heightIn(max = dimensionResource(R.dimen.product_height))
          ProductSectionType.WIDTH_EXPANDED -> Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 2f)
        }
      )
      val contentScale by rememberUpdatedState(
        when (productUiModel.productSectionType) {
          ProductSectionType.NORMAL -> ContentScale.Fit
          ProductSectionType.WIDTH_EXPANDED -> ContentScale.FillWidth
        }
      )
      Image(
        modifier = imageModifier
          .align(Alignment.Center),
        painter = rememberAsyncImagePainter(
          model = productUiModel.image
        ),
        contentScale = contentScale,
        contentDescription = null,
      )
      Image(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .baseClickable(
            onClick = when (productUiModel.isFavorite) {
              true -> onClickedMarkedFavorite
              false -> onClickedUnmarkedFavorite
            }
          ),
        painter = painterResource(
          if (productUiModel.isFavorite) R.drawable.ic_btn_heart_on
          else R.drawable.ic_btn_heart_off),
        contentDescription = null
      )
    }

    Text(
      text = productUiModel.name,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )

    PriceSection(
      priceUiModel = productUiModel.priceUiModel
    )
  }
}

@Preview
@Composable
fun ProductSectionPreview(
  @PreviewParameter(ProductUiModelPreviewParam::class)
  productUiModel: ProductUiModel
) {
  BaseSurface {
    ProductSection(
      productUiModel = productUiModel
    )
  }
}
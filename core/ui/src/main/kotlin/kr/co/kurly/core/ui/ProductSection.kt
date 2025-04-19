package kr.co.kurly.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.rememberAsyncImagePainter
import kr.co.architecture.core.ui.R
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import kr.co.kurly.core.ui.preview.ProductUiModelPreviewParam

data class ProductUiModel(
  val product: SectionProductDtoResponse,
  val productSectionType: ProductSectionType,
  val priceDisplayType: PriceDisplayType
)

enum class ProductSectionType {
  NORMAL,
  WIDTH_EXPANDED
}

@Composable
fun ProductSection(
  modifier: Modifier = Modifier,
  productUiModel: ProductUiModel
) {
  Column(modifier) {
    val imageModifier by rememberUpdatedState(
      when (productUiModel.productSectionType) {
        ProductSectionType.NORMAL -> Modifier
          .width(dimensionResource(R.dimen.product_width))
          .height(dimensionResource(R.dimen.product_height))
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
        .align(Alignment.CenterHorizontally),
      painter = rememberAsyncImagePainter(
        model = productUiModel.product.image
      ),
      contentScale = contentScale,
      contentDescription = null,
    )

    Text(
      text = productUiModel.product.name,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )

    PriceSection(
      priceType = productUiModel.product.price,
      priceDisplayType = productUiModel.priceDisplayType
    )
  }
}

@Preview
@Composable
fun ProductSectionPreview(
  @PreviewParameter(ProductUiModelPreviewParam::class)
  productUiModel: ProductUiModel
) {
  Surface(
    color = Color.White
  ) {
    ProductSection(
      productUiModel = productUiModel
    )
  }
}
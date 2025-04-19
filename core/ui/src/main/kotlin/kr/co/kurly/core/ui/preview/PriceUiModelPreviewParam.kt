package kr.co.kurly.core.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.PriceUiModel
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel

class PriceUiModelPreviewParam : PreviewParameterProvider<PriceUiModel> {
  override val values: Sequence<PriceUiModel> = sequenceOf(
    PriceUiModel(
      priceType = PriceType.Original(10000),
      priceLineType = PriceLineType.ONE_LINE,
    ),
    PriceUiModel(
      priceType = PriceType.Discounted(
        originalPrice = 10000,
        discountedPrice = 8000,
        discountedRate = 20
      ),
      priceLineType = PriceLineType.ONE_LINE,
    ),
    PriceUiModel(
      priceType = PriceType.Discounted(
        originalPrice = 10000,
        discountedPrice = 8000,
        discountedRate = 20
      ),
      priceLineType = PriceLineType.TWO_LINE,
    )
  )
}
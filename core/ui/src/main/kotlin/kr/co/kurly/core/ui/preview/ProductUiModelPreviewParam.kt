package kr.co.kurly.core.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.PriceUiModel
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel

class ProductUiModelPreviewParam : PreviewParameterProvider<ProductUiModel> {
  override val values: Sequence<ProductUiModel> = sequenceOf(
    ProductUiModel(
      id = 1,
      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      name = "맛있는 바나나",
      isFavorite = false,
      productSectionType = ProductSectionType.NORMAL,
      priceUiModel = PriceUiModel(
        priceType = PriceType.Original(10000),
        priceLineType = PriceLineType.ONE_LINE
      )
    ),
    ProductUiModel(
      id = 1,
      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      name = "맛있는 바나나",
      isFavorite = true,
      productSectionType = ProductSectionType.NORMAL,
      priceUiModel = PriceUiModel(
        priceType = PriceType.Discounted(
          originalPrice = 10000,
          discountedPrice = 8000,
          discountedRate = 20
        ),
        priceLineType = PriceLineType.TWO_LINE
      ),
    ),
    ProductUiModel(
      id = 1,
      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      name = "맛있는 바나나",
      isFavorite = false,
      productSectionType = ProductSectionType.WIDTH_EXPANDED,
      priceUiModel = PriceUiModel(
        priceType = PriceType.Original(10000),
        priceLineType = PriceLineType.ONE_LINE,
      )
    ),
    ProductUiModel(
      id = 1,

      image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
      name = "맛있는 바나나",
      isFavorite = true,
      productSectionType = ProductSectionType.WIDTH_EXPANDED,
      priceUiModel = PriceUiModel(
        priceType = PriceType.Discounted(
          originalPrice = 10000,
          discountedPrice = 8000,
          discountedRate = 20
        ),
        priceLineType = PriceLineType.ONE_LINE
      )
    )
  )
}
package kr.co.kurly.core.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.co.kurly.core.model.PriceType
import kr.co.kurly.core.repository.dto.SectionProductDtoResponse
import kr.co.kurly.core.ui.PriceLineType
import kr.co.kurly.core.ui.ProductSectionType
import kr.co.kurly.core.ui.ProductUiModel

class ProductUiModelPreviewParam : PreviewParameterProvider<ProductUiModel> {
  override val values: Sequence<ProductUiModel> = sequenceOf(
    ProductUiModel(
      product = SectionProductDtoResponse(
        id = 1,
        price = PriceType.Original(10000),
        image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
        isSoldOut = false,
        name = "맛있는 바나나",
        isFavorite = false,
      ),
      productSectionType = ProductSectionType.NORMAL,
      priceLineType = PriceLineType.ONE_LINE
    ),
    ProductUiModel(
      product = SectionProductDtoResponse(
        id = 1,
        price = PriceType.Discounted(
          originalPrice = 10000,
          discountedPrice = 8000,
          discountedRate = 20
        ),
        image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
        isSoldOut = false,
        name = "맛있는 바나나",
        isFavorite = false,
      ),
      productSectionType = ProductSectionType.NORMAL,
      priceLineType = PriceLineType.TWO_LINE
    ),
    ProductUiModel(
      product = SectionProductDtoResponse(
        id = 1,
        price = PriceType.Original(10000),
        image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
        isSoldOut = false,
        name = "맛있는 바나나",
        isFavorite = false,
      ),
      productSectionType = ProductSectionType.WIDTH_EXPANDED,
      priceLineType = PriceLineType.ONE_LINE
    ),
    ProductUiModel(
      product = SectionProductDtoResponse(
        id = 1,
        price = PriceType.Discounted(
          originalPrice = 10000,
          discountedPrice = 8000,
          discountedRate = 20
        ),
        image = "https://img-cf.kurly.com/shop/data/goods/165303902534l0.jpg",
        isSoldOut = false,
        name = "맛있는 바나나",
        isFavorite = false,
      ),
      productSectionType = ProductSectionType.WIDTH_EXPANDED,
      priceLineType = PriceLineType.ONE_LINE
    ),

  )
}
package kr.co.kurly.core.model

sealed interface PriceType {
  @JvmInline
  value class Original(val price: Int) : PriceType
  data class Discounted(
    val originalPrice: Int,
    val discountedPrice: Int,
    val discountedRate: Int
  ) : PriceType {
    companion object {
      fun calculateDiscountRate(
        originalPrice: Int,
        discountedPrice: Int
      ): Int {
        return (((originalPrice - discountedPrice).toDouble() / originalPrice) * 100).toInt()
      }
    }
  }
}
package kr.co.kurly.core.network.model

data class SectionProductApiResponse(
    val data: List<Data>
) {
    data class Data(
        val discountedPrice: Int,
        val id: Int,
        val image: String,
        val isSoldOut: Boolean,
        val name: String,
        val originalPrice: Int
    )
}
package kr.co.kurly.core.network.model

data class SectionApiResponse(
    val data: List<Data>,
    val paging: Paging
) {
    data class Data(
        val id: Int,
        val title: String,
        val type: String,
        val url: String
    )
    data class Paging(
        val next_page: Int
    )
}
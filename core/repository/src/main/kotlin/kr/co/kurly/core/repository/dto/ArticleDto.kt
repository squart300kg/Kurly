package kr.co.kurly.core.repository.dto

import kr.co.kurly.core.network.model.ArticleResponse

data class ArticleDto(
  val name: String
) {
  companion object {
    fun mapperToDto(article: List<ArticleResponse>) =
      article.map { ArticleDto(it.title) }
  }
}


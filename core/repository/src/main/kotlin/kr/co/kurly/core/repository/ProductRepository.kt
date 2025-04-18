package kr.co.kurly.core.repository

import kotlinx.coroutines.flow.Flow
import kr.co.kurly.core.repository.dto.ArticleDto

interface ProductRepository {

  fun getList(): Flow<List<ArticleDto>>

}
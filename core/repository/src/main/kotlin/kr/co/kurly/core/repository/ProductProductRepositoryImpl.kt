package kr.co.kurly.core.repository

import com.skydoves.sandwich.suspendOperator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.kurly.core.network.operator.ResponseBaseOperator
import kr.co.kurly.core.repository.dto.ArticleDto
import javax.inject.Inject

class ProductProductRepositoryImpl @Inject constructor(
  private val remoteApi: RemoteApi
) : ProductRepository {

  override fun getList(): Flow<List<ArticleDto>> {
    return flow {
      remoteApi.getList().suspendOperator(
        ResponseBaseOperator(
          mapper = ArticleDto::mapperToDto,
          onSuccess = ::emit
        )
      )
    }
  }
}



package kr.co.kurly.core.network.operator

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import com.skydoves.sandwich.retrofit.raw
import kr.co.kurly.core.model.ArchitectureSampleHttpException
import kr.co.kurly.core.network.model.CommonApiResponse

class ResponseBaseOperator<ENTITY>(
  private val onSuccess: suspend (data: CommonApiResponse<ENTITY>) -> Unit
) : ApiResponseSuspendOperator<CommonApiResponse<ENTITY>>() {

  override suspend fun onSuccess(apiResponse: ApiResponse.Success<CommonApiResponse<ENTITY>>) {
    onSuccess(apiResponse.data)
  }

  override suspend fun onError(apiResponse: ApiResponse.Failure.Error) {
    throw ArchitectureSampleHttpException(
      code = apiResponse.raw.code,
      message = apiResponse.raw.message,
    )
  }

  override suspend fun onException(apiResponse: ApiResponse.Failure.Exception) {
    throw apiResponse.throwable
  }
}
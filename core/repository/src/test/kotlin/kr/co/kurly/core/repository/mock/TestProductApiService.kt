package kr.co.kurly.core.repository.mock

import com.skydoves.sandwich.ApiResponse
import kr.co.kurly.core.network.ProductApiService
import kr.co.kurly.core.network.model.CommonApiResponse
import kr.co.kurly.core.network.model.SectionApiResponse
import kr.co.kurly.core.network.model.SectionProductApiResponse

class TestProductApiService: ProductApiService {
  override suspend fun getSections(page: Int): ApiResponse<CommonApiResponse<SectionApiResponse>> {
    return ApiResponse.of { getSectionApiResponse(page) }
  }

  override suspend fun getSectionProduct(sectionId: Int): ApiResponse<CommonApiResponse<SectionProductApiResponse>> {
    return ApiResponse.of { getSectionProductApiResponse(sectionId) }
  }
}
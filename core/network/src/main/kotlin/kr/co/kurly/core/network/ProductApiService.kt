package kr.co.kurly.core.network

import com.skydoves.sandwich.ApiResponse
import kr.co.kurly.core.network.model.CommonApiResponse
import kr.co.kurly.core.network.model.SectionApiResponse
import kr.co.kurly.core.network.model.SectionProductApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {

  @GET("/sections")
  suspend fun getSections(
    @Query("page") page: Int
  ): ApiResponse<CommonApiResponse<SectionApiResponse>>

  @GET("/section/products")
  suspend fun getSectionProduct(
    @Query("sectionId") sectionId: Int
  ): ApiResponse<CommonApiResponse<SectionProductApiResponse>>

}
package kr.co.kurly.core.network

import kr.co.kurly.core.network.model.SectionApiResponse
import kr.co.kurly.core.network.model.SectionProductApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

  @GET("/sections")
  suspend fun getSections(@Query("page") page: Int): List<SectionApiResponse>

  @GET("/section/products")
  suspend fun getSectionProduct(@Query("sectionId") sectionId: Int): SectionProductApiResponse

}
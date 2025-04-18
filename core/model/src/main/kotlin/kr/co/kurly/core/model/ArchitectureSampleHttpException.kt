package kr.co.kurly.core.model

data class ArchitectureSampleHttpException(
  val code: Int,
  override val message: String
) : Exception(message)
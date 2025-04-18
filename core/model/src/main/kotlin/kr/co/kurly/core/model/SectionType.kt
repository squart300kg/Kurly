package kr.co.kurly.core.model

enum class SectionType(val value: String) {
  NONE(""),
  HORIZONTAL("horizontal"),
  VERTICAL("vertical"),
  GRID("grid");

  companion object {
    fun creator(value: String): SectionType {
      return entries.find { it.value == value } ?: NONE
    }
  }
}
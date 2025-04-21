plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.kurly.core.domain"

  defaultConfig { }

  dependencies {
    api(project(":core:model"))
    api(project(":core:repository"))
  }
}
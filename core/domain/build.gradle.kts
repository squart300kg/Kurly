plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.architecture.core.domain"

  defaultConfig { }

  dependencies {
    implementation(project(":core:model"))
    api(project(":core:repository"))
  }
}
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
  alias(libs.plugins.architecture.sample.ui)
}

android {
  namespace = "kr.co.kurly.core.ui"

  defaultConfig { }

  dependencies {
    implementation(project(":core:model"))
    implementation(project(":testing"))
  }
}
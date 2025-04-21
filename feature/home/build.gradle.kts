plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
  alias(libs.plugins.architecture.sample.ui)
}

android {
  namespace = "kr.co.kurly.feature.home"

  defaultConfig { }

  dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:repository"))
    implementation(project(":testing"))
  }
}
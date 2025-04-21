plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.architecture.sample.base.setting)
  alias(libs.plugins.architecture.sample.ui)
  alias(libs.plugins.androidx.baseline.profile)
}

android {
  namespace = "kr.co.kurly.app"

  defaultConfig { }

  dependencies {
    implementation(project(":feature:home"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:repository"))
  }
}


plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.architecture.sample.base.setting)
  alias(libs.plugins.architecture.sample.ui)
}

android {
  namespace = "kr.co.kurly.app"

  defaultConfig { }

  dependencies {
    implementation(project(":feature:first"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:repository"))
    implementation(project(":core:common"))
  }
}


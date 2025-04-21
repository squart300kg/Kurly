plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.kurly.core.repository"

  defaultConfig { }

  dependencies {
    api(project(":core:network"))
    api(project(":core:database"))

    implementation(libs.com.github.skydoves.sandwich)
  }
}


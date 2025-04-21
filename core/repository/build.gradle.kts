plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.architecture.core.repository"

  defaultConfig { }

  dependencies {
    implementation(project(":core:model"))
    api(project(":core:network"))
    api(project(":core:database"))

    implementation(libs.com.github.skydoves.sandwich)
  }
}


plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.test.testing"

  defaultConfig { }

  dependencies {
    api(project(":core:model"))
    api(project(":core:domain"))
    api(project(":core:repository"))

    implementation(libs.androidx.test.rules)
    implementation(libs.kotlinx.coroutines.test)
  }
}
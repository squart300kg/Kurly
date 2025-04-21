plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.kurly.core.database"

  defaultConfig { }

  dependencies {
    api(project(":core:model"))

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
  }
}
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

android {
  namespace = "kr.co.architecture.core.database"

  defaultConfig { }

  dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
  }
}
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.architecture.sample.base.setting)
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
  namespace = "kr.co.kurly.core.network"

  defaultConfig {
    buildConfigField("String", "apiUrl", "${properties["apiUrl"]}")
  }

  dependencies {
    api(project(":core:model"))

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.com.github.skydoves.sandwich)
    implementation(libs.com.github.skydoves.sandwich.retrofit)
  }
}


plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.jetbrains.kotlin)
  id("androidx.baselineprofile") version libs.versions.baselineProfile
}

android {
  namespace = "kr.co.kurly.benchmarks"
  compileSdk = 35

  defaultConfig {
    minSdk = 26
    targetSdk = 35

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  targetProjectPath = ":app"
  experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
  implementation(libs.androidx.test.ext.junit)
  implementation(libs.androidx.espresso.core)
  implementation(libs.androidx.uiautomator)
  implementation(libs.androidx.benchmark.macro.junit4)
}
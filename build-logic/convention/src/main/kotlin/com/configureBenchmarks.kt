package com

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.TestExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureBenchmarks(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  pluginManager.apply("org.jetbrains.kotlin.android")

  (commonExtension as TestExtension).apply {
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

    dependencies {
      add("implementation", libs.findLibrary("androidx-test-ext-junit").get())
      add("implementation", libs.findLibrary("androidx-espresso-core").get())
      add("implementation", libs.findLibrary("androidx-uiautomator").get())
      add("implementation", libs.findLibrary("androidx-benchmark-macro-junit4").get())
    }
  }
}
package com

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureBenchmarks(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  pluginManager.apply("org.jetbrains.kotlin.android")
  pluginManager.apply("com.android.test")

  commonExtension.apply {
    dependencies {
//      add("implementation", libs.findLibrary("androidx-test-ext-junit").get())
//      add("implementation", libs.findLibrary("androidx-espresso-core").get())
//      add("implementation", libs.findLibrary("androidx-uiautomator").get())
//      add("implementation", libs.findLibrary("androidx-benchmark-macro-junit4").get())
    }
  }
}
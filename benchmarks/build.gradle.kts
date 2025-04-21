plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.architecture.sample.benchmarks)
  alias(libs.plugins.androidx.baseline.profile)
}

android {
  namespace = "kr.co.kurly.benchmarks"

  defaultConfig { }
}

dependencies { }
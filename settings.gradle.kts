pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Kurly"
include(":app")
include(":feature:home")
include(":core:ui")
include(":core:model")
include(":core:network")
include(":core:repository")
include(":core:database")
include(":core:domain")
include(":testing")
include(":benchmarks")
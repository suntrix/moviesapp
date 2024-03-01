import java.util.Properties

fun username(): String = with(Properties()) {
    load(rootProject.projectDir.resolve("local.properties").inputStream())
    getProperty("gpr.user") ?: System.getenv("GITHUB_USER")
}

fun password(): String = with(Properties()) {
    load(rootProject.projectDir.resolve("local.properties").inputStream())
    getProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.github.com/kotlin-multiplatform/common") {
            credentials {
                username = username()
                password = password()
            }
        }
    }
}

rootProject.name = "MoviesApp"
include(":app-android")
include(":app-shared")

include(":shared:logging")
include(":shared:omdb")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.goncalossilvaResources)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

//    jvm()

    iosArm64()
    iosSimulatorArm64()
//    iosX64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kmp.common.ktor)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
//            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.goncalossilva.resources)
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
        }
//        jvmMain.dependencies {
//            implementation(libs.logback.classic)
//        }
    }
}

buildkonfig {
    packageName = "suntrix.kmp.moviesapp.shared.omdb"

    defaultConfigs {
        val apiKey: String = gradleLocalProperties(rootProject.projectDir).getProperty("omdb.apiKey")

        require(apiKey.isNotEmpty()) {
            "OMDb API key not set in local.properties `omdb.apiKey`"
        }

        buildConfigField(FieldSpec.Type.STRING, "API_KEY", apiKey)
    }
}

android {
    namespace = "suntrix.kmp.moviesapp.shared.omdb"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

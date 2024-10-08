import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.goncalossilvaResources)
    alias(libs.plugins.kodeinMock)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    explicitApi()

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
            implementation(libs.kodein.mock)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.shared.logging)
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
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "true")

        val properties = Properties()
        properties.load(rootProject.file("local.properties").inputStream())
        val apiKey: String = properties.getProperty("omdb.apiKey").also {
            require(it.isNotEmpty()) {
                "OMDb API key not set in local.properties `omdb.apiKey`"
            }
        }

        buildConfigField(FieldSpec.Type.STRING, "API_KEY", apiKey)
    }

    defaultConfigs("release") {
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "false")
    }
}

mockmp {
    usesHelper = true
    installWorkaround()
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

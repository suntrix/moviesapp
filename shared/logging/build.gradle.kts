import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.kotlinMultiplatform)
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
            implementation(libs.kodein.log)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

buildkonfig {
    packageName = "suntrix.kmp.moviesapp.shared.logging"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "true")
    }

    defaultConfigs("release") {
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", "false")
    }
}

android {
    namespace = "suntrix.kmp.moviesapp.shared.logging"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

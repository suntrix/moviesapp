plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
//        iosX64()
    ).forEach {
        it.binaries.framework {
            baseName = "MoviesAppShared"
            isStatic = true
            export(projects.shared.omdb)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.shared.omdb)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "suntrix.kmp.moviesapp.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

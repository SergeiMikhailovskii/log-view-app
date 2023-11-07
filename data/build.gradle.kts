plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.nativeCocoapods)
    alias(libs.plugins.serialization)
    alias(libs.plugins.mockmp)
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinParcelize.get().pluginId)
    id("com.arkivanov.parcelize.darwin")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            isStatic = true
            baseName = "data"
        }

        pod("FirebaseAnalytics")
        pod("FirebaseCrashlytics")
        pod("UXCam")
        pod("FreshchatSDK", "5.8.0")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.bundles.apiDataCommonRemote)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.bundles.apiDataIOSRemote)
            }
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.mikhailovskii.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
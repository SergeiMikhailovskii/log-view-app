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
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = true
            baseName = "presentation"
            export(libs.debugview)
            export(libs.decompose)
            export(libs.decompose.essenty.lifecycle)
            export(libs.decompose.essenty.stateKeeper)
            export(libs.decompose.parcelize)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.data)
                api(libs.decompose)
                api(libs.decompose.essenty.lifecycle)
                api(libs.decompose.essenty.stateKeeper)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.decompose.parcelize)
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.mikhailovskii.logviewapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
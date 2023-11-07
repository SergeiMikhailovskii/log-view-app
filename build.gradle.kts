plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.nativeCocoapods).apply(false)
    alias(libs.plugins.google.ksp).apply(false)
    id("com.arkivanov.parcelize.darwin").version("0.2.2").apply(false)
}
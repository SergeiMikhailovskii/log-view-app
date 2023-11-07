package com.mikhailovskii.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
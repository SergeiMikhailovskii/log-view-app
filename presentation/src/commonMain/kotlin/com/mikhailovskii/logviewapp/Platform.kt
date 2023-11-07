package com.mikhailovskii.logviewapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
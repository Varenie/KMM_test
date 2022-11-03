package com.varenie.kmmtest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.goytai.myfirstkmpproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
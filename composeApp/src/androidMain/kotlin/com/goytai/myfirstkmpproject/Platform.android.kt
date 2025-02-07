package com.goytai.myfirstkmpproject

import android.os.Build
import com.goytai.myfirstkmpproject.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
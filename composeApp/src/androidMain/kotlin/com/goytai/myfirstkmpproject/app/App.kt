package com.goytai.myfirstkmpproject.app

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class App : Application() {
  companion object {
    var context: Context by Delegates.notNull()
      private set
  }

  override fun onCreate() {
    super.onCreate()

    context = this.applicationContext
  }
}
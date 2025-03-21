package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

val themeSettingsScreenDi = DI.Module("ThemeSettingsScreenDI") {
  bindFactory { params: ThemeSettingsScreenModelParams ->
    ThemeSettingsScreenModel(
      params = params,
      appSettingsRepository = instance()
    )
  }
}
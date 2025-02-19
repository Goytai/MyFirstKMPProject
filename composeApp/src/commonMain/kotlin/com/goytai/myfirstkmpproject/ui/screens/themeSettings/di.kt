package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

val themeSettingsScreenDi = DI.Module("ThemeSettingsScreenDI") {
  bindFactory { params: ScreenModelParams ->
    ThemeSettingsScreenModel(
      params = params,
      appSettingsRepository = instance()
    )
  }
}
package com.goytai.myfirstkmpproject.ui.screens.settings

import org.kodein.di.DI
import org.kodein.di.bindFactory

val settingsScreenDi = DI.Module("SettingsScreenDI") {
  bindFactory { params: SettingsScreenModelParams -> SettingsScreenModel(params = params) }
}
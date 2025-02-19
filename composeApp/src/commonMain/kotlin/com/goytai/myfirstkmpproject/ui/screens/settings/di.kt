package com.goytai.myfirstkmpproject.ui.screens.settings

import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import org.kodein.di.DI
import org.kodein.di.bindFactory

val settingsScreenDi = DI.Module("SettingsScreenDI") {
  bindFactory { params: ScreenModelParams -> SettingsScreenModel(params = params) }
}
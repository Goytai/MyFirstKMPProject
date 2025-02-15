package com.goytai.myfirstkmpproject.infra.di

import com.goytai.myfirstkmpproject.ui.screens.home.HomeScreenModel
import com.goytai.myfirstkmpproject.ui.screens.settings.SettingsScreenModel
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.ThemeSettingsScreenModel
import org.kodein.di.DI
import org.kodein.di.bindFactory

val screenModelsDi = DI.Module(name = "screenModels") {
  bindFactory { params: ScreenModelParams -> HomeScreenModel(params) }
  bindFactory { params: ScreenModelParams -> SettingsScreenModel(params) }
  bindFactory { params: ScreenModelParams -> ThemeSettingsScreenModel(params) }
}

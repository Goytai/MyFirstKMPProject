package com.goytai.myfirstkmpproject.infra.di

import com.goytai.myfirstkmpproject.ui.screens.home.homeScreenDi
import com.goytai.myfirstkmpproject.ui.screens.settings.settingsScreenDi
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.themeSettingsScreenDi
import org.kodein.di.DI

val screenModelsDi = DI.Module(name = "ScreensDI") {
  importAll(
    homeScreenDi,
    settingsScreenDi,
    themeSettingsScreenDi,
  )
}

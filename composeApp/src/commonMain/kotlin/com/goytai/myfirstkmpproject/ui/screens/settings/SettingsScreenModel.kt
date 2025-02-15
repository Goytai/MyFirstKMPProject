package com.goytai.myfirstkmpproject.ui.screens.settings

import cafe.adriel.voyager.core.model.ScreenModel
import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.ThemeSettingsScreen

class SettingsScreenModel(private val params: ScreenModelParams) : ScreenModel {
  private val di = params.di
  private val navigator = params.navigator


  // Public Methods
  fun handleNavigateToBack() {
    navigator.pop()
  }

  fun handleNavigateToThemeSettings() {
    navigator.push(ThemeSettingsScreen())
  }
}
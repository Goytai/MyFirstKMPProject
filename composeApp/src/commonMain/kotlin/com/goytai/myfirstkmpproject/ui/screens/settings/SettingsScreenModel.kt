package com.goytai.myfirstkmpproject.ui.screens.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.ThemeSettingsScreen


internal data class SettingsScreenModelParams(val navigator: Navigator)

internal class SettingsScreenModel(private val params: SettingsScreenModelParams) : ScreenModel {
  private val navigator = params.navigator


  // Public Methods
  fun handleNavigateToBack() {
    navigator.pop()
  }

  fun handleNavigateToThemeSettings() {
    navigator.push(ThemeSettingsScreen())
  }
}
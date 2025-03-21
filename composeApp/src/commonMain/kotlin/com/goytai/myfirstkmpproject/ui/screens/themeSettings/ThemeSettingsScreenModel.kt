package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.goytai.myfirstkmpproject.AppTheme
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal data class ThemeSettingsScreenModelParams(val navigator: Navigator)

internal class ThemeSettingsScreenModel(
  private val params: ThemeSettingsScreenModelParams,
  private val appSettingsRepository: IAppSettingsRepository
) : ScreenModel {
  private val navigator = params.navigator

  val appTheme: Flow<AppTheme> get() = appSettingsRepository.getAppTheme()


  // Public Methods
  fun handleOnBack() {
    navigator.pop()
  }

  fun handleOnChangeAppThemeMode(mode: AppTheme) {
    screenModelScope.launch {
      appSettingsRepository.updateAppTheme(mode)
    }
  }
}

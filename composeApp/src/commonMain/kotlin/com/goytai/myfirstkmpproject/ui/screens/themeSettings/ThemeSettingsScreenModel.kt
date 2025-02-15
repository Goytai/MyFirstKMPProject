package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.goytai.myfirstkmpproject.AppTheme
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.kodein.di.instance

class ThemeSettingsScreenModel(private val params: ScreenModelParams) : ScreenModel {
  private val di = params.di
  private val navigator = params.navigator

  private val _appSettingsRepository: IAppSettingsRepository by di.instance()

  val appTheme: Flow<AppTheme> get() = _appSettingsRepository.getAppTheme()
  

  // Public Methods
  fun handleOnBack() {
    navigator.pop()
  }

  fun handleOnChangeAppThemeMode(mode: AppTheme) {
    screenModelScope.launch {
      _appSettingsRepository.updateAppTheme(mode)
    }
  }
}

package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.AppTheme
import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.components.ThemeOptions

class ThemeSettingsScreen : Screen {
  @Composable
  override fun Content() {
    val screenModel = rememberScreenModel<ScreenModelParams, ThemeSettingsScreenModel>(
      arg = ScreenModelParams(navigator = LocalNavigator.currentOrThrow)
    )

    val appTheme by screenModel.appTheme.collectAsState(initial = AppTheme.SYSTEM)


    ScreenContainer {
      ScreenHeader(
        title = "Tema",
        onBack = { screenModel.handleOnBack() }
      )

      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ThemeOptions(
          icon = Icons.Outlined.AutoAwesome,
          title = "Automático",
          isSelected = appTheme == AppTheme.SYSTEM,
          onClick = { screenModel.handleOnChangeAppThemeMode(AppTheme.SYSTEM) }
        )
        ThemeOptions(
          icon = Icons.Outlined.LightMode,
          title = "Claro",
          isSelected = appTheme == AppTheme.LIGHT,
          onClick = { screenModel.handleOnChangeAppThemeMode(AppTheme.LIGHT) }
        )
        ThemeOptions(
          icon = Icons.Outlined.DarkMode,
          title = "Escuro",
          isSelected = appTheme == AppTheme.DARK,
          onClick = { screenModel.handleOnChangeAppThemeMode(AppTheme.DARK) }
        )
      }
    }
  }
}
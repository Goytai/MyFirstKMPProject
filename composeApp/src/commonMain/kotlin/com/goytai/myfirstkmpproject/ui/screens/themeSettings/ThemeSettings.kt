package com.goytai.myfirstkmpproject.ui.screens.themeSettings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.AppTheme
import com.goytai.myfirstkmpproject.data.repository.AppSettingsRepository
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.components.ThemeOptions
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.instance

class ThemeSettings : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()

        val di = localDI()
        val AppSettingsRepository: IAppSettingsRepository by di.instance()
        val appTheme by AppSettingsRepository.getAppTheme().collectAsState(initial = AppTheme.SYSTEM)

        fun handleOnBack() {
            navigator.pop()
        }

        fun handleOnChangeAppThemeMode(mode: AppTheme) {
            coroutineScope.launch {
                AppSettingsRepository.updateAppTheme(mode)
            }
        }

        ScreenContainer {
            ScreenHeader(
                title = "Tema",
                onBack = { handleOnBack() }
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ThemeOptions(
                    icon = Icons.Outlined.AutoAwesome,
                    title = "Automático",
                    isSelected = appTheme == AppTheme.SYSTEM,
                    onClick = { handleOnChangeAppThemeMode(AppTheme.SYSTEM) }
                )
                ThemeOptions(
                    icon = Icons.Outlined.LightMode,
                    title = "Claro",
                    isSelected = appTheme == AppTheme.LIGHT,
                    onClick = { handleOnChangeAppThemeMode(AppTheme.LIGHT) }
                )
                ThemeOptions(
                    icon = Icons.Outlined.DarkMode,
                    title = "Escuro",
                    isSelected = appTheme == AppTheme.DARK,
                    onClick = { handleOnChangeAppThemeMode(AppTheme.DARK) }
                )
            }
        }
    }
}
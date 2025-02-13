package com.goytai.myfirstkmpproject.ui.screens.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.settings.components.SettingOption
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.ThemeSettings

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        fun handleNavigateToBack() {
            navigator.pop()
        }

        fun handleNavigateToThemeSettings() {
            navigator.push(ThemeSettings())
        }

        ScreenContainer {
            ScreenHeader(
                title = "Configurações",
                onBack = { handleNavigateToBack() },
            )

            SettingOption (
                icon = Icons.Outlined.DarkMode,
                title = "Tema",
                description = "Configura o tema do aplicativo",
                onClick = { handleNavigateToThemeSettings() }
            )

            SettingOption (
                icon = Icons.Rounded.DeleteOutline,
                title = "Apagar dados",
                description = "Apaga todas suas tarefas e configurações",
                onClick = {}
            )
        }
    }
}

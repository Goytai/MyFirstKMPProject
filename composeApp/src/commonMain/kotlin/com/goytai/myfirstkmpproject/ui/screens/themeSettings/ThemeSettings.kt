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
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.themeSettings.components.ThemeOptions

class ThemeSettings : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        fun handleOnBack() {
            navigator.pop()
        }

        ScreenContainer {
            ScreenHeader(
                title = "Tema",
                onBack = { handleOnBack() }
            )

            Column (verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ThemeOptions(
                    icon = Icons.Outlined.AutoAwesome,
                    title = "Automático",
                    isSelected = true,
                    onClick = { }
                )
                ThemeOptions(
                    icon = Icons.Outlined.LightMode,
                    title = "Claro",
                    isSelected = false,
                    onClick = {  }
                )
                ThemeOptions(
                    icon = Icons.Outlined.DarkMode,
                    title = "Escuro",
                    isSelected = false,
                    onClick = {  }
                )
            }
        }
    }
}
package com.goytai.myfirstkmpproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import com.goytai.myfirstkmpproject.infra.di.di
import com.goytai.myfirstkmpproject.ui.screens.home.HomeScreen
import com.goytai.myfirstkmpproject.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import org.kodein.di.instance

@Composable
@Preview
fun App() {
    val appSettingsRepository by di.instance<IAppSettingsRepository>()
    val appThemeMode by appSettingsRepository.getAppTheme().collectAsState(initial = AppTheme.SYSTEM)

    withDI(di = di) {
        AppTheme(mode = appThemeMode) {
            Navigator(HomeScreen())
        }
    }
}
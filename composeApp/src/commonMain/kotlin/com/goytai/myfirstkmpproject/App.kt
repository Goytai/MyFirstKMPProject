package com.goytai.myfirstkmpproject

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.goytai.myfirstkmpproject.infra.di.di
import com.goytai.myfirstkmpproject.ui.screens.home.HomeScreen
import com.goytai.myfirstkmpproject.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI

@Composable
@Preview
fun App() {
    withDI(di = di) {
        AppTheme {
            Navigator(HomeScreen())
        }
    }
}
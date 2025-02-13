package com.goytai.myfirstkmpproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenContainer(content: @Composable ColumnScope.() -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets(0.dp, 0.dp, 0.dp, 0.dp))
            .safeDrawingPadding()
    ) {
        Column (
            modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 26.dp),
            content = content
        )
    }
}

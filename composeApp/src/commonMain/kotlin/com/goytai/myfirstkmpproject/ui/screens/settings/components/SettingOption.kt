package com.goytai.myfirstkmpproject.ui.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingOption(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    ListItem(
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp)
            )
        },
        headlineContent = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        },
        supportingContent = {
            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        modifier = Modifier.clickable { onClick() }
    )
}


@Preview
@Composable
private fun SettingOptionPreview() {
    SettingOption(
        icon = Icons.Outlined.DarkMode,
        title = "Tema",
        description = "Configura o tema do aplicativo",
        onClick = {}
    )
}
package com.goytai.myfirstkmpproject.ui.screens.themeSettings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

private data class ThemeOptionsVariants (
    val backgroundColor: Color,
    val borderColor: Color,
    val fontColor: Color,
    val fontWeight: FontWeight
)

@Composable
fun ThemeOptions(
    icon: ImageVector,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val variant = when (isSelected) {
        true -> {
            ThemeOptionsVariants(
                borderColor = MaterialTheme.colorScheme.primaryContainer,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                fontColor = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold
            )
        }
        false -> {
            ThemeOptionsVariants(
                borderColor = MaterialTheme.colorScheme.surfaceContainer,
                backgroundColor = MaterialTheme.colorScheme.background,
                fontColor = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal
            )
        }
    }

    Card (
        colors = CardDefaults.cardColors(containerColor = variant.backgroundColor),
        border = BorderStroke(width = 2.dp, color = variant.borderColor),
        onClick = { onClick() }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding( vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Icon (
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
            )

            Text (
                text = title,
                fontSize = 18.sp,
                fontWeight = variant.fontWeight,
                color = variant.fontColor
            )
        }
    }
}

@Preview
@Composable
private fun ThemeOptionsPreview() {
    ThemeOptions(
        icon = Icons.Outlined.AutoAwesome,
        title = "Automático",
        isSelected = false,
        onClick = {}
    )
}
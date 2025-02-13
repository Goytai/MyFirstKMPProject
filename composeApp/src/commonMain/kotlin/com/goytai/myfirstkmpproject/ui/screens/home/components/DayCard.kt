package com.goytai.myfirstkmpproject.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames

private data class DayCardVariants (
    val background: Color,
    val borderColor: Color,
    val fontColor: Color
)

@Composable
fun DayCard(
    date: LocalDate,
    isSelected: Boolean = false,
) {

    val variant = when (isSelected) {
        true -> {
            DayCardVariants(
                fontColor = Color.Black,
                background = MaterialTheme.colorScheme.primaryContainer,
                borderColor = MaterialTheme.colorScheme.primaryContainer,
            )
        }
        false -> {
            DayCardVariants(
                fontColor = Color.Gray,
                background = MaterialTheme.colorScheme.background,
                borderColor = MaterialTheme.colorScheme.surfaceContainer,
            )
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = variant.background),
        border = BorderStroke(width = 2.dp, color = variant.borderColor)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                color = variant.fontColor,
                fontWeight = FontWeight.Bold,
                text = date.format(LocalDate.Format {
                    dayOfWeek(
                        DayOfWeekNames(
                            monday = "SEG",
                            tuesday = "TER",
                            wednesday = "QUA",
                            thursday = "QUI",
                            friday = "SEX",
                            saturday = "SAB",
                            sunday = "DOM"
                        )
                    )
                }),
            )
            Text(
                color = variant.fontColor,
                fontWeight = FontWeight.Bold,
                text = date.format(LocalDate.Format {
                    dayOfMonth()
                }),
            )
        }
    }
}
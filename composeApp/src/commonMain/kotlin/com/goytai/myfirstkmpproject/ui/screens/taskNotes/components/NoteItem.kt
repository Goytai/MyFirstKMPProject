package com.goytai.myfirstkmpproject.ui.screens.taskNotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoteItem(
  text: String
) {
  Card(
    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    border = BorderStroke(
      width = 1.dp,
      color = MaterialTheme.colorScheme.outlineVariant
    ),
    modifier = Modifier.fillMaxWidth(),
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
      Icon(
        imageVector = Icons.Rounded.EditNote,
        contentDescription = "Note",
        modifier = Modifier.size(32.dp)
      )

      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
          text = "Nota",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          color = MaterialTheme.colorScheme.onBackground
        )

        Text(
          text = text,
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.secondary
        )
      }
    }
  }
}

@Preview
@Composable
private fun NoteItemPreview() {
  NoteItem(
    text = "Hoje eu cheguei atrasado",
  )
}
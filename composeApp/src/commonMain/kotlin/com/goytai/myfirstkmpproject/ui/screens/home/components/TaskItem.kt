package com.goytai.myfirstkmpproject.ui.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goytai.myfirstkmpproject.data.model.Task

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(onClick = { onClick() }, modifier = Modifier.padding(bottom=16.dp)) {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = task.isDone, onCheckedChange = { onClick() })

            Text(text = task.name, fontSize = 17.sp)
        }
    }
}
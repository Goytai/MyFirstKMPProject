package com.goytai.myfirstkmpproject.ui.screens.taskNotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.ui.components.Input
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.taskNotes.components.NoteItem
import com.goytai.myfirstkmpproject.ui.utils.formatInstantToLocalDate

data class TaskNotesScreen(val task: Task) : Screen {

  @Composable
  override fun Content() {
    val screenModel = rememberScreenModel<TaskNotesScreenModelParams, TaskNotesScreenModel>(
      arg = TaskNotesScreenModelParams(task = task, navigator = LocalNavigator.currentOrThrow)
    )

    val taskNotes by screenModel.taskNotes.collectAsState()
    val newTaskNoteInput by screenModel.newTaskNoteInput.collectAsState()

    ScreenContainer {
      ScreenHeader(
        title = task.name,
        onBack = { screenModel.handleOnBack() }
      )

      Column(modifier = Modifier.padding(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
          text = "Criado em ${formatInstantToLocalDate(task.createdAt)}",
          color = MaterialTheme.colorScheme.secondary
        )

        if (task.isDone && task.doneAt != null) {
          Text(
            text = "Concluído em ${formatInstantToLocalDate(task.createdAt)}",
            color = MaterialTheme.colorScheme.secondary
          )
        }
      }

      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
      ) {
        taskNotes.forEach {
          NoteItem(
            text = it.text
          )
        }
      }

      Column(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Input(
          value = newTaskNoteInput,
          modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 112.dp),
          singleLine = false,
          onValueChange = { screenModel.handleOnChangeNewTaskNoteInput(it) },
          placeholder = {
            Text(text = "Adicione uma nota ...", fontSize = 16.sp)
          },
        )
        Button(
          onClick = { screenModel.handleOnCreateNewTaskNote() },
          modifier = Modifier.fillMaxWidth().height(56.dp),
          enabled = newTaskNoteInput.trim().isNotEmpty(),
          shape = RoundedCornerShape(12.dp),
        ) {
          Text(text = "Criar", fontSize = 16.sp)
        }
      }
    }
  }
}
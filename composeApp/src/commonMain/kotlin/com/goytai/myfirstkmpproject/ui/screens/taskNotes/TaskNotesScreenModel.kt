package com.goytai.myfirstkmpproject.ui.screens.taskNotes

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.data.model.TaskNote
import com.goytai.myfirstkmpproject.domain.repository.ITaskNoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal data class TaskNotesScreenModelParams(
  val task: Task,
  val navigator: Navigator,
)

internal class TaskNotesScreenModel(
  private val params: TaskNotesScreenModelParams,
  private val taskNoteRepository: ITaskNoteRepository
) : ScreenModel {

  private val task = params.task
  private val navigator = params.navigator

  private var _newTaskNoteInput = MutableStateFlow("")
  val newTaskNoteInput = _newTaskNoteInput.asStateFlow()

  private var _taskNotes = MutableStateFlow(mutableListOf<TaskNote>())
  val taskNotes = _taskNotes.asStateFlow()

  // Init
  init {
    fetchTaskNotes()
  }

  // Private Methos
  private fun fetchTaskNotes() {
    screenModelScope.launch {
      val notes = taskNoteRepository.getTaskNotesByTaskId(id = task.id)
      _taskNotes.update {
        notes.toMutableList()
      }
    }
  }

  // Public Methods
  fun handleOnChangeNewTaskNoteInput(value: String) {
    _newTaskNoteInput.update { value }
  }

  fun handleOnCreateNewTaskNote() {
    if (_newTaskNoteInput.value.trim().isEmpty()) return

    val newTaskNote = TaskNote(text = _newTaskNoteInput.value, taskId = task.id)

    _taskNotes.update {
      it.add(newTaskNote)
      it
    }

    screenModelScope.launch {
      taskNoteRepository.insertTaskNote(newTaskNote)
    }

    _newTaskNoteInput.value = ""
  }

  fun handleOnBack() {
    navigator.pop()
  }
}
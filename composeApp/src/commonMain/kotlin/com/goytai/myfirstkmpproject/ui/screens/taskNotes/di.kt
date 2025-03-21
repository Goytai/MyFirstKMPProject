package com.goytai.myfirstkmpproject.ui.screens.taskNotes

import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

val taskNotesScreenDi = DI.Module("TaskNotesScreenDI") {
  bindFactory { params: TaskNotesScreenModelParams ->
    TaskNotesScreenModel(
      params = params,
      taskNoteRepository = instance()
    )
  }
}
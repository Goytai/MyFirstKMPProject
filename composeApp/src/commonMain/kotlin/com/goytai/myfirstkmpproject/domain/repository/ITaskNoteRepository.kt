package com.goytai.myfirstkmpproject.domain.repository

import com.goytai.myfirstkmpproject.data.model.TaskNote

interface ITaskNoteRepository {
  suspend fun getTaskNote(id: String): TaskNote?

  suspend fun insertTaskNote(note: TaskNote)

  suspend fun deleteTaskNote(note: TaskNote)

  suspend fun updateTaskNote(note: TaskNote)

  suspend fun getTaskNotesByTaskId(id: String): List<TaskNote>
}
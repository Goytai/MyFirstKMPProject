package com.goytai.myfirstkmpproject.data.repository

import com.goytai.myfirstkmpproject.data.model.TaskNote
import com.goytai.myfirstkmpproject.domain.repository.ITaskNoteRepository
import com.goytai.myfirstkmpproject.domain.services.ITaskNoteDao

class TaskNoteRepository(private val taskNoteDao: ITaskNoteDao) : ITaskNoteRepository {
  override suspend fun getTaskNote(id: String): TaskNote? = taskNoteDao.getTaskNote(id)

  override suspend fun insertTaskNote(note: TaskNote) = taskNoteDao.insert(note)

  override suspend fun deleteTaskNote(note: TaskNote) = taskNoteDao.delete(note)

  override suspend fun updateTaskNote(note: TaskNote) = taskNoteDao.update(note)

  override suspend fun getTaskNotesByTaskId(id: String): List<TaskNote> = taskNoteDao.getTaskNotesByTaskId(id)

}
package com.goytai.myfirstkmpproject.domain.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.goytai.myfirstkmpproject.data.model.TaskNote

@Dao
interface ITaskNoteDao {
  @Insert
  suspend fun insert(note: TaskNote)

  @Update
  suspend fun update(note: TaskNote)

  @Delete
  suspend fun delete(note: TaskNote)

  @Query("SELECT * from tasks_notes WHERE id = :id")
  suspend fun getTaskNote(id: String): TaskNote?

  @Query("SELECT * FROM tasks_notes WHERE task_id = :taskId")
  suspend fun getTaskNotesByTaskId(taskId: String): List<TaskNote>
}
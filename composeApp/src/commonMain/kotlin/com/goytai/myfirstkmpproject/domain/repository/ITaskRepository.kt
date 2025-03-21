package com.goytai.myfirstkmpproject.domain.repository

import com.goytai.myfirstkmpproject.data.model.Task
import kotlinx.datetime.LocalDate

interface ITaskRepository {
  suspend fun getAllTasks(): List<Task>

  suspend fun getTask(id: String): Task?

  suspend fun insertTask(item: Task)

  suspend fun deleteTask(item: Task)

  suspend fun updateTask(item: Task)

  suspend fun getTasksByScheduleDate(scheduleDate: LocalDate): List<Task>
}
package com.goytai.myfirstkmpproject.data.repository

import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import com.goytai.myfirstkmpproject.domain.services.ITaskDao
import kotlinx.datetime.LocalDate

class TaskRepository(private val taskDao: ITaskDao) : ITaskRepository {
    override suspend fun getAllTasks(): List<Task> = taskDao.getAllTasks()

    override suspend fun getTask(id: String): Task? = taskDao.getTask(id)

    override suspend fun insertTask(item: Task) = taskDao.insert(item)

    override suspend fun deleteTask(item: Task) = taskDao.delete(item)

    override suspend fun updateTask(item: Task) = taskDao.update(item)

    override suspend fun getTasksByScheduleDate(scheduleDate: LocalDate): List<Task> =
        taskDao.getTasksByScheduleDate(scheduleDate)
}
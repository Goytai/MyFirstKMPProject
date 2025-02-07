package com.goytai.myfirstkmpproject.domain.repository

import com.goytai.myfirstkmpproject.data.model.Task

interface ITaskRepository {
    /**
     * Retrieve all the tasks from the the given data source.
     */
    suspend fun getAllTasks(): List<Task>

    /**
     * Retrieve an task from the given data source that matches with the [id].
     */
    suspend fun getTask(id: Int): Task?

    /**
     * Insert task in the data source
     */
    suspend fun insertTask(item: Task)

    /**
     * Delete task from the data source
     */
    suspend fun deleteTask(item: Task)

    /**
     * Update task in the data source
     */
    suspend fun updateTask(item: Task)
}
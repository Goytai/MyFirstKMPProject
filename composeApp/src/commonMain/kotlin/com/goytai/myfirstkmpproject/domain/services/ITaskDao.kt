package com.goytai.myfirstkmpproject.domain.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.goytai.myfirstkmpproject.data.model.Task
import kotlinx.datetime.LocalDate

@Dao
interface ITaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * from tasks WHERE id = :id")
    suspend fun getTask(id: String): Task?

    @Query("SELECT * from tasks ORDER BY name ASC")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE schedule_date = :scheduleDate")
    suspend fun getTasksByScheduleDate(scheduleDate: LocalDate): List<Task>
}
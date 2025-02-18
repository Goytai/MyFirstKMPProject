package com.goytai.myfirstkmpproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goytai.myfirstkmpproject.domain.model.ITask
import kotlinx.datetime.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey override val id: String = Uuid.random().toString(),
    override val name: String,
    override var isDone: Boolean,
    override val scheduleDate: LocalDate,
    override val createdAt: Instant = Clock.System.now()
) : ITask
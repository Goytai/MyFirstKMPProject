package com.goytai.myfirstkmpproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goytai.myfirstkmpproject.domain.model.ITask
import kotlinx.datetime.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: String = Uuid.random().toString(),

    @ColumnInfo(name = "name")
    override val name: String,

    @ColumnInfo(name = "is_done")
    override var isDone: Boolean,

    @ColumnInfo(name = "done_at")
    override val doneAt: Instant? = null,

    @ColumnInfo(name = "schedule_date")
    override val scheduleDate: LocalDate,

    @ColumnInfo(name = "created_at")
    override val createdAt: Instant = Clock.System.now(),
) : ITask
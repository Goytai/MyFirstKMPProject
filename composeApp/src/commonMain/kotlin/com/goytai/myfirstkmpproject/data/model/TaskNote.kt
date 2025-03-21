package com.goytai.myfirstkmpproject.data.model

import androidx.room.*
import com.goytai.myfirstkmpproject.domain.model.ITaskNote
import kotlinx.datetime.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(
  tableName = "tasks_notes",
  foreignKeys = [
    ForeignKey(
      entity = Task::class,
      parentColumns = arrayOf("id"),
      childColumns = arrayOf("task_id"),
      onUpdate = ForeignKey.CASCADE,
      onDelete = ForeignKey.CASCADE
    )
  ]
)
data class TaskNote(
  @PrimaryKey
  @ColumnInfo(name = "id")
  override val id: String = Uuid.random().toString(),

  @ColumnInfo(name = "text")
  override val text: String,

  @ColumnInfo(name = "task_id", index = true)
  override val taskId: String,

  @ColumnInfo(name = "created_at")
  override val createdAt: Instant = Clock.System.now(),
) : ITaskNote
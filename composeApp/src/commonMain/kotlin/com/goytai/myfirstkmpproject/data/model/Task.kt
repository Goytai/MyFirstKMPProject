package com.goytai.myfirstkmpproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey var id: String = Uuid.random().toString(),
    val name: String,
    var isDone: Boolean
)
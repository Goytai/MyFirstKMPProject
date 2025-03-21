package com.goytai.myfirstkmpproject.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

interface ITask {
    val id: String
    val name: String
    var isDone: Boolean
    val doneAt: Instant?
    val scheduleDate: LocalDate
    val createdAt: Instant
}

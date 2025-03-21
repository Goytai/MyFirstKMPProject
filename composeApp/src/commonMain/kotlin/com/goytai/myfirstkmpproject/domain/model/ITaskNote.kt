package com.goytai.myfirstkmpproject.domain.model

import kotlinx.datetime.Instant

interface ITaskNote {
  val id: String
  val text: String
  val taskId: String
  val createdAt: Instant
}

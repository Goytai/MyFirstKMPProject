package com.goytai.myfirstkmpproject.ui.utils

import kotlinx.datetime.*
import kotlinx.datetime.format.char

val systemTimeZone = TimeZone.currentSystemDefault()

fun formatLocalDate(date: LocalDate): String {
  return date.format(
    LocalDate.Format {
      dayOfMonth(); char('/'); monthNumber(); char('/'); year()
    }
  )
}

fun formatInstantToLocalDateTime(instant: Instant): String {
  return instant.toLocalDateTime(systemTimeZone).format(
    LocalDateTime.Format {
      dayOfMonth(); char('/'); monthNumber(); char('/'); year(); char(' '); hour(); char(':'); minute();
    }
  )
}

fun formatInstantToLocalDate(instant: Instant): String {
  return instant.toLocalDateTime(systemTimeZone).format(
    LocalDateTime.Format {
      dayOfMonth(); char('/'); monthNumber(); char('/'); year();
    }
  )
}
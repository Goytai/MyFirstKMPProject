package com.goytai.myfirstkmpproject.infra.database

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class Converters {
  @TypeConverter
  fun fromDateString(value: String?): LocalDate? {
    return value?.let { LocalDate.parse(it) }
  }

  @TypeConverter
  fun localDateToString(date: LocalDate?): String? {
    return date?.toString()
  }

  @TypeConverter
  fun fromISOString(value: String?): Instant? {
    return value?.let { Instant.parse(value) }
  }

  @TypeConverter
  fun instantToISOString(instant: Instant?): String? {
    return instant?.toString()
  }
}
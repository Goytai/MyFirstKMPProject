package com.goytai.myfirstkmpproject.infra.datastore.serializers

import androidx.datastore.core.okio.OkioSerializer
import com.goytai.myfirstkmpproject.AppSettings
import okio.BufferedSink
import okio.BufferedSource
import okio.IOException

object AppSettingsSerializer : OkioSerializer<AppSettings> {
  override val defaultValue: AppSettings
    get() = AppSettings()

  override suspend fun readFrom(source: BufferedSource): AppSettings {
    try {
      return AppSettings.ADAPTER.decode(source)
    } catch (exception: IOException) {
      throw Exception(exception.message ?: "Serialization Exception")
    }
  }

  override suspend fun writeTo(t: AppSettings, sink: BufferedSink) {
    sink.write(t.encode())
  }
}
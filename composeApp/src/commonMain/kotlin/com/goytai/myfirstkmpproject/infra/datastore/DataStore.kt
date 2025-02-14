package com.goytai.myfirstkmpproject.infra.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import com.goytai.myfirstkmpproject.AppSettings
import com.goytai.myfirstkmpproject.infra.datastore.serializers.AppSettingsSerializer
import okio.FileSystem
import okio.Path

internal const val DATA_STORE_FILE_NAME = "app_settings_datastore.preferences_pb"

fun createDataStore(fileSystem: FileSystem, producePath: () -> Path): DataStore<AppSettings> =
    DataStoreFactory.create(
        storage = OkioStorage(
            fileSystem = fileSystem,
            producePath = producePath,
            serializer = AppSettingsSerializer,
        ),
    )

expect fun createDataStore(): DataStore<AppSettings>

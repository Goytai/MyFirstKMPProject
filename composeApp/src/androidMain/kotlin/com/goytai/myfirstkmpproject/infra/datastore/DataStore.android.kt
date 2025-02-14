package com.goytai.myfirstkmpproject.infra.datastore

import androidx.datastore.core.DataStore
import com.goytai.myfirstkmpproject.AppSettings
import com.goytai.myfirstkmpproject.MainActivity
import okio.FileSystem
import okio.Path.Companion.toPath

actual fun createDataStore(): DataStore<AppSettings> {
    val appContext = MainActivity.instance.applicationContext
    val producePath = { appContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath.toPath() }

    return createDataStore(fileSystem = FileSystem.SYSTEM, producePath = producePath)
}
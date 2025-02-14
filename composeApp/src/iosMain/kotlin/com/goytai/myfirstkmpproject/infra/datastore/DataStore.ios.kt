package com.goytai.myfirstkmpproject.infra.datastore

import androidx.datastore.core.DataStore
import com.goytai.myfirstkmpproject.AppSettings
import kotlinx.cinterop.ExperimentalForeignApi
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask


actual fun createDataStore(): DataStore<AppSettings> {
    @OptIn(ExperimentalForeignApi::class)
    val producePath: () -> Path = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        (requireNotNull(documentDirectory).path + "/$DATA_STORE_FILE_NAME").toPath()
    }

    return createDataStore(fileSystem = FileSystem.SYSTEM, producePath = producePath)
}
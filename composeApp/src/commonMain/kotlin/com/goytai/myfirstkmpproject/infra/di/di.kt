package com.goytai.myfirstkmpproject.infra.di

import androidx.datastore.core.DataStore
import com.goytai.myfirstkmpproject.AppSettings
import com.goytai.myfirstkmpproject.data.repository.AppSettingsRepository
import com.goytai.myfirstkmpproject.data.repository.TaskRepository
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import com.goytai.myfirstkmpproject.domain.services.ITaskDao
import com.goytai.myfirstkmpproject.infra.database.AppDatabase
import com.goytai.myfirstkmpproject.infra.database.getAppDatabase
import com.goytai.myfirstkmpproject.infra.datastore.createDataStore
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val di = DI {
    import(screenModelsDi)

    bindSingleton<DataStore<AppSettings>> { createDataStore() }

    bindSingleton<IAppSettingsRepository> { AppSettingsRepository(dataStore = instance()) }

    bindSingleton<AppDatabase> { getAppDatabase() }

    bindSingleton<ITaskDao> { instance<AppDatabase>().getTaskDao() }

    bindSingleton<ITaskRepository> { TaskRepository(taskDao = instance()) }
}
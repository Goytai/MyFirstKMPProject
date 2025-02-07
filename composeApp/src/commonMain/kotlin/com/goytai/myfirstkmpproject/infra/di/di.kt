package com.goytai.myfirstkmpproject.infra.di

import com.goytai.myfirstkmpproject.data.repository.TaskRepository
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import com.goytai.myfirstkmpproject.domain.services.ITaskDao
import com.goytai.myfirstkmpproject.infra.database.AppDatabase
import com.goytai.myfirstkmpproject.infra.database.getAppDatabase
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val di = DI {
    bindSingleton<AppDatabase>{ getAppDatabase() }

    bindSingleton<ITaskDao>{ instance<AppDatabase>().getTaskDao() }

    bindSingleton<ITaskRepository>{  TaskRepository(taskDao = instance())  }
}
package com.goytai.myfirstkmpproject.infra.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.domain.services.ITaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

@ConstructedBy(AppDatabaseConstructor::class)
@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getTaskDao(): ITaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getAppDatabase(): AppDatabase {
    return getDatabaseBuilder()
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
            .setQueryCoroutineContext(Dispatchers.IO)
            .setDriver(BundledSQLiteDriver())
            .build()
}


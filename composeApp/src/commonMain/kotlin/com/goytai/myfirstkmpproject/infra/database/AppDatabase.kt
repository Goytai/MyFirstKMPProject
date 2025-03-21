package com.goytai.myfirstkmpproject.infra.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.data.model.TaskNote
import com.goytai.myfirstkmpproject.domain.services.ITaskDao
import com.goytai.myfirstkmpproject.domain.services.ITaskNoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

fun getAppDatabase(): AppDatabase {
    return getDatabaseBuilder()
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .setQueryCoroutineContext(Dispatchers.IO)
        .setDriver(BundledSQLiteDriver())
        .build()
}

@ConstructedBy(AppDatabaseConstructor::class)
@Database(
    version = 1, exportSchema = true, entities = [
        Task::class,
        TaskNote::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): ITaskDao

    abstract fun getTaskNoteDao(): ITaskNoteDao
}



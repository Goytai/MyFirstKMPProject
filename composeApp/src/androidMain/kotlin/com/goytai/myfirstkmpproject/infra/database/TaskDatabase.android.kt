package com.goytai.myfirstkmpproject.infra.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.goytai.myfirstkmpproject.MainActivity

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = MainActivity.instance.applicationContext
    val dbFile = appContext.getDatabasePath("app_database.db")

    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
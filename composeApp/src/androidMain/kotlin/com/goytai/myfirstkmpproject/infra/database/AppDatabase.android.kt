package com.goytai.myfirstkmpproject.infra.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.goytai.myfirstkmpproject.app.App

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = App.context
    val dbFile = appContext.getDatabasePath("app_database.db")

    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
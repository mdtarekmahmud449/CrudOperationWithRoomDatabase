package com.example.simplecontact.data.loacal_database

import android.content.Context
import androidx.room.Room

object Db {
    fun instance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "contact-database"
        ).allowMainThreadQueries().build()
    }
}
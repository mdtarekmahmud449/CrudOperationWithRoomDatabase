package com.example.simplecontact.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplecontact.dao.ContactDao
import com.example.simplecontact.database_model.UserContact

@Database(entities = [UserContact::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    public abstract fun contactDao(): ContactDao
}
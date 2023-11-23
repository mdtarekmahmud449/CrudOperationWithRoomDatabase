package com.example.simplecontact.data.loacal_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserContact::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    public abstract fun contactDao(): ContactDao

}
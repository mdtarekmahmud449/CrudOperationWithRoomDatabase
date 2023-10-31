package com.example.simplecontact.database_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserContact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "USER_NAME")
    val name: String,
    @ColumnInfo(name = "USER_EMAIL")
    val email: String,
    @ColumnInfo(name = "USER_PHONE")
    val phone: String
)

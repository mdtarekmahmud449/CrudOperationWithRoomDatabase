package com.example.simplecontact.data.loacal_database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class UserContact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "USER_NAME")
    var name: String,
    @ColumnInfo(name = "USER_EMAIL")
    var email: String,
    @ColumnInfo(name = "USER_PHONE")
    var phone: String
): Parcelable

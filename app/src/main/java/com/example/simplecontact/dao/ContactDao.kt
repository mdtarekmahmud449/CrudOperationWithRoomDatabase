package com.example.simplecontact.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.simplecontact.database_model.UserContact

@Dao
interface ContactDao {
    @Insert
    fun insertUserContact(userContact: UserContact)

    @Update
    fun updateUserContact(userContact: UserContact)

    @Delete
    fun deleteUserContact(userContact: UserContact)

    @Query("SELECT * FROM UserContact")
    fun getAllUserContacts(): List<UserContact>

    @Query("SELECT * FROM UserContact WHERE id IN (:uid)")
    fun loadAllContactsByUid(uid: IntArray): List<UserContact>

    @Query("SELECT * FROM UserContact WHERE USER_NAME LIKE :name LIMIT 1")
    fun findByUserContactName(name: String): List<UserContact>
}
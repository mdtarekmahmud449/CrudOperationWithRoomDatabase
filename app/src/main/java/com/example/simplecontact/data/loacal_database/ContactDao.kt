package com.example.simplecontact.data.loacal_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Insert
    fun insertUserContact(userContact: UserContact)

    @Insert
    fun insertAllUser(vararg userContact: UserContact)

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
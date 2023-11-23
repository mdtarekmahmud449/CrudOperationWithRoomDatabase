package com.example.simplecontact.data

import com.example.simplecontact.data.loacal_database.ContactDao

class Datasource(private val contactDao: ContactDao) {
    fun getAll() = contactDao.getAllUserContacts()
}
package com.example.simplecontact.di

import android.content.Context
import com.example.simplecontact.data.Datasource
import com.example.simplecontact.data.loacal_database.Db
import com.example.simplecontact.data.repository.ContactRepository
import com.example.simplecontact.views.viewmodels.ContactViewModelFactory

class ObjectContainer (private val applicationContext: Context) {
    private val dao = Db.instance(applicationContext).contactDao()
    private val datasource = Datasource(dao)
    private val contactRepository = ContactRepository(datasource)
    val contactViewModelFactory = ContactViewModelFactory(contactRepository)
}
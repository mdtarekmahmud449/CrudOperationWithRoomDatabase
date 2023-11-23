package com.example.simplecontact.data.repository

import com.example.simplecontact.data.Datasource

class ContactRepository(private val datasource: Datasource) {
    fun getAll() = datasource.getAll()
}
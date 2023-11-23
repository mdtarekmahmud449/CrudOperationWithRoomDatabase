package com.example.simplecontact.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecontact.data.loacal_database.UserContact
import com.example.simplecontact.data.repository.ContactRepository

class ContactViewModel(private val contactRepository: ContactRepository) : ViewModel() {
    private val _responseAllContact = MutableLiveData<List<UserContact>>()
    val responseAllContact: LiveData<List<UserContact>> = _responseAllContact

    fun getAllContact(){
        val data =contactRepository.getAll()
        _responseAllContact.postValue(data)
    }
}
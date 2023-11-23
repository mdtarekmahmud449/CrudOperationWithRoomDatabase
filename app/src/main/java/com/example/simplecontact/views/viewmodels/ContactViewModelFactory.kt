package com.example.simplecontact.views.viewmodels

import com.example.simplecontact.data.repository.ContactRepository


interface Factory<T>{
    fun create(): T
}
class ContactViewModelFactory(private val contactRepository: ContactRepository) : Factory<ContactViewModel>{
    override fun create(): ContactViewModel {
        return ContactViewModel(contactRepository)
    }
}
package com.example.simplecontact.di

import android.app.Application
import android.content.Context

class ContactApplication: Application() {
    fun appContainer(context: Context) : ObjectContainer{
        return ObjectContainer(context)
    }
}
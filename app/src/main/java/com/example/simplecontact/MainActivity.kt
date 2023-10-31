package com.example.simplecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.simplecontact.adapter.ContactAdapter
import com.example.simplecontact.dao.ContactDao
import com.example.simplecontact.database.AppDatabase
import com.example.simplecontact.database_model.UserContact
import com.example.simplecontact.databinding.ActivityMainBinding
import com.example.simplecontact.screen.ContactActivity

class MainActivity : AppCompatActivity(), ContactAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var db: AppDatabase
    private lateinit var contactDao: ContactDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "contact-database"
        ).allowMainThreadQueries().build()
        contactDao = db.contactDao()

        addContact()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        contactAdapter = ContactAdapter(contactDao.getAllUserContacts(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = contactAdapter
    }



    private fun addContact() {
        binding.addContactBtn.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
            finish()
        }
    }

    override fun onCreateDelete(userContact: UserContact) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Are you sure?")
            .setMessage("want to delete this?")
            .setIcon(R.drawable.baseline_warning_amber_24)
            .setPositiveButton(
                "Delete"
            ) { _, _ ->


                db.contactDao().deleteUserContact(userContact)
                Log.d("TAG", "onContactDelete: ${userContact.toString()}  is deleted")
                setRecyclerView()
            }
            .setNegativeButton(
                "Dismis"
            ) { _, _ -> }

        val alerts = alertDialog.create()
        alerts.show()
    }
}
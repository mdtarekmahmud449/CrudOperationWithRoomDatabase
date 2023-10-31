package com.example.simplecontact.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.room.Room
import com.example.simplecontact.MainActivity
import com.example.simplecontact.database.AppDatabase
import com.example.simplecontact.database_model.UserContact
import com.example.simplecontact.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "contact-database"
        ).allowMainThreadQueries().build()

        val contactDao = db.contactDao()


        binding.saveContactBtn.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val phone = binding.phoneInput.text.toString().trim()

            val userContact = UserContact(name = name, email = email, phone = phone)

            if(name.isBlank() || email.isBlank() || phone.isBlank()){
                Toast.makeText(this, "Please fill the all inputs", Toast.LENGTH_SHORT).show()
            }
            else if(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                contactDao.insertUserContact(userContact)
                Toast.makeText(this, "Data saved to local database", Toast.LENGTH_SHORT).show()
                binding.nameInput.text = null
                binding.emailInput.text = null
                binding.phoneInput.text = null
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this, "Pleas put a valid email", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

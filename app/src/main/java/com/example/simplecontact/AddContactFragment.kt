package com.example.simplecontact

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.simplecontact.database.AppDatabase
import com.example.simplecontact.database.Db
import com.example.simplecontact.database_model.UserContact
import com.example.simplecontact.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val contactDao = Db.instance(requireContext()).contactDao()


        binding.saveContactBtn.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val phone = binding.phoneInput.text.toString().trim()

            val userContact = UserContact(name = name, email = email, phone = phone)

            if (name.isBlank() || email.isBlank() || phone.isBlank()) {
                Toast.makeText(requireContext(), "Please fill the all inputs", Toast.LENGTH_SHORT).show()
            } else if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                contactDao.insertUserContact(userContact)
                Toast.makeText(requireContext(), "Data saved to local database", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()

            } else {
                Toast.makeText(requireContext(), "Pleas put a valid email", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
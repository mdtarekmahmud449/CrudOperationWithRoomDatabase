package com.example.simplecontact.views.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simplecontact.data.loacal_database.Db
import com.example.simplecontact.data.loacal_database.UserContact
import com.example.simplecontact.databinding.FragmentContactUpdateBinding


class ContactUpdateFragment : Fragment() {
    private lateinit var binding: FragmentContactUpdateBinding
    private var userContact: UserContact? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userContact = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable("contact", UserContact::class.java)
        }
        else{
            arguments?.getParcelable("contact")
        }

        userContact?.let {
            binding.nameInput.setText(it.name)
            binding.emailInput.setText(it.email)
            binding.phoneInput.setText(it.phone)
        }

        binding.updateContactBtn.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val phone = binding.phoneInput.text.toString().trim()

            userContact?.let {
                it.name = name
                it.email = email
                it.phone = phone

                Db.instance(requireContext()).contactDao().updateUserContact(it)
                findNavController().popBackStack()
            }
        }
    }
}
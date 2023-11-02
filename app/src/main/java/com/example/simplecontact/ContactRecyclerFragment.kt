package com.example.simplecontact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.simplecontact.adapter.ContactAdapter
import com.example.simplecontact.dao.ContactDao
import com.example.simplecontact.database.AppDatabase
import com.example.simplecontact.database_model.UserContact
import com.example.simplecontact.databinding.FragmentContactRecyclerBinding

class ContactRecyclerFragment : Fragment(), ContactAdapter.Listener {
    private lateinit var binding: FragmentContactRecyclerBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var db: AppDatabase
    private lateinit var contactDao: ContactDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "contact-database"
        ).allowMainThreadQueries().build()
        contactDao = db.contactDao()
        setRecyclerView()

        binding.floatingActionBtn.setOnClickListener{
            findNavController().navigate(R.id.addContactFragment)
        }
    }

    private fun setRecyclerView() {
        contactAdapter = ContactAdapter(contactDao.getAllUserContacts(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = contactAdapter
    }

    override fun onCreateDelete(userContact: UserContact) {
        val alertDialog = AlertDialog.Builder(requireContext())
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
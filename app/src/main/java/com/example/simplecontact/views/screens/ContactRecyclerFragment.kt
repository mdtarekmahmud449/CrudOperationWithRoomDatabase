package com.example.simplecontact.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplecontact.R
import com.example.simplecontact.views.adapters.ContactAdapter
import com.example.simplecontact.data.loacal_database.ContactDao
import com.example.simplecontact.data.loacal_database.Db
import com.example.simplecontact.data.loacal_database.UserContact
import com.example.simplecontact.databinding.FragmentContactRecyclerBinding
import com.example.simplecontact.di.ContactApplication
import com.example.simplecontact.di.ObjectContainer
import com.example.simplecontact.views.viewmodels.ContactViewModel

class ContactRecyclerFragment : Fragment(), ContactAdapter.Listener {
    private lateinit var binding: FragmentContactRecyclerBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var contactDao: ContactDao
    private lateinit var viewModel: ContactViewModel
    private lateinit var objectContainer: ObjectContainer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactRecyclerBinding.inflate(inflater, container, false)

        objectContainer = ContactApplication().appContainer(requireContext())
        viewModel = objectContainer.contactViewModelFactory.create()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setRecyclerView()
        viewModel.getAllContact()

        binding.floatingActionBtn.setOnClickListener {
            findNavController().navigate(R.id.action_contactRecyclerFragment_to_addContactFragment)
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.responseAllContact.observe(viewLifecycleOwner) {list ->
            contactAdapter = ContactAdapter(list, this)
            binding.recyclerView.adapter = contactAdapter
        }
    }

    override fun onCreateDelete(userContact: UserContact) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("want to delete this?")
            .setIcon(R.drawable.baseline_warning_amber_24)
            .setPositiveButton(
                "Delete"
            ) { _, _ ->

                Db.instance(requireContext()).contactDao().deleteUserContact(userContact)
                setRecyclerView()
            }
            .setNegativeButton(
                "Dismiss"
            ) { _, _ -> }

        val alerts = alertDialog.create()
        alerts.show()
    }

    override fun onUpdateContact(userContact: UserContact) {
        val bundle = Bundle()
        bundle.putParcelable("contact", userContact)
        findNavController().navigate(R.id.contactUpdateFragment, bundle)
    }
}
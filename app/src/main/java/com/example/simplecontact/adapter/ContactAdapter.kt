package com.example.simplecontact.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecontact.database_model.UserContact
import com.example.simplecontact.databinding.SampleViewBinding

class ContactAdapter(val userContactList: List<UserContact>, val listener: ContactAdapter.Listener) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    interface Listener{
        fun onCreateDelete(userContact: UserContact)
    }
    class MyViewHolder(val binding: SampleViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SampleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userContactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        userContactList[position].let {
            holder.binding.apply {
                nameTv.text = it.name
                emailTv.text = it.email
                phoneTv.text = it.phone
            }
            holder.binding.root.setOnLongClickListener { clicked ->
                Log.d("TAG", "Long clicked: ${it.id}")
                listener.onCreateDelete(it)
                true
            }
        }
    }
}
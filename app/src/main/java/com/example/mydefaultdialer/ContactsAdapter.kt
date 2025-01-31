package com.example.mydefaultdialer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ContactsAdapter(
    private val contacts: List<Contacts>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInitial: TextView = itemView.findViewById(R.id.tv_initial)
        val tvContactName: TextView = itemView.findViewById(R.id.tv_contact_name)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tv_number_display)
        val cardView: MaterialCardView = itemView.findViewById(R.id.mcv_name_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        holder.tvInitial.text = contact.initial
        holder.tvContactName.text = contact.name
        holder.tvPhoneNumber.text = contact.phoneNumber
    }

    override fun getItemCount(): Int = contacts.size
}

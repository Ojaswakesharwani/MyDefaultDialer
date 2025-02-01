package com.example.mydefaultdialer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlankFragment_contact_touch : Fragment() {
    private var contactName: String? = null
    private var contactNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactName = it.getString("param1")
            contactNumber = it.getString("param2")
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.nestedfragment_contact_touch, container, false)


        val tvName: TextView = view.findViewById(R.id.text_caller)
        val tvNumber: TextView = view.findViewById(R.id.numbernested)


        tvName.text = contactName
        tvNumber.text = contactNumber

        return view
    }
}
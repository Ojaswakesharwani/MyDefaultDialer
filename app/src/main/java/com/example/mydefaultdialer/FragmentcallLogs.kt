package com.example.mydefaultdialer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydefaultdialer.databinding.FragmentContactsBinding
import com.example.mydefaultdialer.databinding.FragmentFragmentcallLogsBinding

class FragmentcallLogs : Fragment() {

    private var _binding: FragmentFragmentcallLogsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.mcvDialer.setOnClickListener{
                openDialPad()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragmentcallLogsBinding.inflate(inflater,container,false)
        return  binding.root
    }

    private fun openDialPad() {
        // Replace the fragment or layout with the dial pad fragment or layout
        val dialPadFragment = DialPad()  // Assuming DialPad is a fragment that holds your dialpad UI
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, dialPadFragment)  // Replace 'container' with your container ID
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Avoid memory leaks
    }
}
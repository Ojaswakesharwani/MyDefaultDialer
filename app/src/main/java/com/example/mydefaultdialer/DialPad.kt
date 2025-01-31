package com.example.mydefaultdialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mydefaultdialer.databinding.DialpadBinding

class DialPad : Fragment(R.layout.dialpad) {

    private var _binding: DialpadBinding? = null
    private val binding get() = _binding!!

    private val CALL_PERMISSION_REQUEST_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the binding
        _binding = DialpadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createDialPad()

        // Set button listeners
        binding.btnCall.setOnClickListener {
            val number = binding.tvNumberDisplay.text.toString()
            if (number.isNotEmpty()) {
                if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE)
                    == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    makeCall(number)
                } else {
                    // Request permission if not granted
                    requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), CALL_PERMISSION_REQUEST_CODE)
                }
            } else {
                Toast.makeText(activity, "Enter a number to call", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            binding.tvNumberDisplay.text = ""
        }
    }

    private fun createDialPad() {
        val buttons = arrayOf(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "*", "0", "#"
        )

        for (buttonText in buttons) {
            val button = Button(requireContext()).apply {
                text = buttonText
                textSize = 18f
                setBackgroundResource(R.drawable.bg_round)
                setOnClickListener {
                    binding.tvNumberDisplay.append(buttonText)
                }
            }

            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(8, 8, 8, 8)
            }

            binding.glDialpad.addView(button, params)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Avoid memory leaks
    }

    private fun makeCall(number: String) {
        val dialUri = Uri.parse("tel:$number")
        val dialIntent = Intent(Intent.ACTION_CALL, dialUri)
        startActivity(dialIntent)
    }

    // Handle the permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                val number = binding.tvNumberDisplay.text.toString()
                makeCall(number)
            } else {
                Toast.makeText(activity, "Permission denied. Cannot make the call.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

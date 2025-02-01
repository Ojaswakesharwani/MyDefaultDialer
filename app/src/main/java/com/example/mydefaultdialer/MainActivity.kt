package com.example.mydefaultdialer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mydefaultdialer.FragmentContacts
import com.example.mydefaultdialer.FragmentcallLogs
import com.example.mydefaultdialer.R
import com.example.mydefaultdialer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Apply edge-to-edge insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize bottom navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_call_logs -> {
                    loadFragment(FragmentcallLogs()) // Load Call Logs fragment
                    true
                }
                R.id.nav_contacts -> {
                    loadFragment(FragmentContacts()) // Load Contacts fragment
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        loadFragment(FragmentcallLogs())
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}
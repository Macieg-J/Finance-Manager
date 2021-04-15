package com.example.financemanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.financemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val newEntry: Intent = Intent(this, EntryActivity::class.java)

        val floatingActionButton: View = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener { _ ->
            startActivity(newEntry)
        }
    }
}
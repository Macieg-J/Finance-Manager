package com.example.financemanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financemanager.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEntryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.entryCancelButton.setOnClickListener { _ -> finish() }

    }
}
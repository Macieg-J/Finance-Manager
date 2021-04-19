package com.example.financemanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.financemanager.databinding.ActivityEntryBinding
import com.example.financemanager.model.FinanceModel

class EntryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEntryBinding.inflate(layoutInflater) }
    private lateinit var category: String
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.entryCancelButton.setOnClickListener { _ -> finish() }
        binding.entryConfirmButton.setOnClickListener { _ ->
            val createdFinanceModel = FinanceModel(
                resources.getDrawable(R.drawable.ic_launcher_foreground, theme).toBitmap(),
                binding.entryCategoryEditText.text.toString(),
                binding.entryPlaceEditText.text.toString(),
                binding.entryValueEditText.text.toString(),
                binding.entryDateEditText.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("ENTRY_INFO", createdFinanceModel)
            setResult(
                Activity.RESULT_OK,
                resultIntent
            ) // fixme https://developer.android.com/training/basics/intents/result
            finish()
        }
    }
}

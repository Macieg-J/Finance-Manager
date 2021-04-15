package com.example.financemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.financemanager.databinding.ActivityEntryBinding
import com.example.financemanager.model.FinanceModel

class EntryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEntryBinding.inflate(layoutInflater) }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.entryCancelButton.setOnClickListener { _ -> finish() }
        binding.entryConfirmButton.setOnClickListener { _ ->
            val createdFinanceModel = FinanceModel(
                resources.getDrawable(R.drawable.ic_launcher_foreground, theme).toBitmap(),
                "XXX",
                binding.entryNameEditText.text.toString(),
                binding.entryValueEditText.text.toString(),
                binding.entryDateEditText.text.toString()
            )
           val returnIntent = Intent(this, MainActivity::class.java)
            returnIntent.putExtra("RESULT", createdFinanceModel) // fixme https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
        }
    }
}

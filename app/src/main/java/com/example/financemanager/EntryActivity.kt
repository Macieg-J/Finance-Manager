package com.example.financemanager

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.financemanager.databinding.ActivityEntryBinding
import com.example.financemanager.model.FinanceModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EntryActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private val binding by lazy { ActivityEntryBinding.inflate(layoutInflater) }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fetchedFinanceModel = intent.getParcelableExtra<FinanceModel>("ENTRY_TO_EDIT")
        val spinner = binding.entryCategorySpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent!!.getItemAtPosition(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@EntryActivity, "Category should be selected", LENGTH_LONG)
                    .show()
            }
        }

        if (fetchedFinanceModel != null) {
            recreateToEdit(fetchedFinanceModel)
        }

        binding.entryCancelButton.setOnClickListener { _ -> finish() }
        binding.entryConfirmButton.setOnClickListener { _ ->
            lateinit var createdFinanceModel: FinanceModel
            if (fetchedFinanceModel == null) {
                createdFinanceModel = FinanceModel(
                    UUID.randomUUID().toString(),
                    resources.getDrawable(R.drawable.ic_launcher_foreground, theme).toBitmap(),
                    binding.entryCategorySpinner.getItemAtPosition(spinner.selectedItemId.toInt())
                        .toString(),
                    binding.entryPlaceEditText.text.toString(),
                    binding.entryValueEditText.text.toString(),
                    exchangeStringToDate(binding.entryDateValueTextView.text.toString())
                )
            } else {
                createdFinanceModel = FinanceModel(
                    fetchedFinanceModel.id,
                    resources.getDrawable(R.drawable.ic_launcher_foreground, theme).toBitmap(),
                    binding.entryCategorySpinner.getItemAtPosition(spinner.selectedItemId.toInt())
                        .toString(),
                    binding.entryPlaceEditText.text.toString(),
                    binding.entryValueEditText.text.toString(),
                    exchangeStringToDate(binding.entryDateValueTextView.text.toString())
                )
            }
            val resultIntent = Intent()
            resultIntent.putExtra("ENTRY_INFO", createdFinanceModel)
            setResult(
                Activity.RESULT_OK,
                resultIntent
            )
            finish()
        }
    }

    fun showDatePickerDialog(view: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun recreateToEdit(financeModel: FinanceModel) {
        binding.entryPlaceEditText.setText(financeModel.place)
        binding.entryValueEditText.setText(financeModel.cost)
        binding.entryDateValueTextView.text = financeModel.date.toString()
    }

    private fun exchangeStringToDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        binding.entryDateValueTextView.text = LocalDate.of(year, month + 1, dayOfMonth).toString()
    }
}

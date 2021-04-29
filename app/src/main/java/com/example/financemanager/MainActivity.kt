package com.example.financemanager

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financemanager.adapter.FinanceAdapter
import com.example.financemanager.databinding.ActivityMainBinding
import com.example.financemanager.model.FinanceModel
import java.time.LocalDate
import java.time.Month
import java.time.Month.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val financeAdapter by lazy { FinanceAdapter(this::onEditClicked, this::onRemoveAction) }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultModel = result.data?.getParcelableExtra<FinanceModel>("ENTRY_INFO")!!
                mapOfAllMonths[resultModel.date.month]!!.replaceIfExists(resultModel)
            }
        }
// todo zamiast 12 adapterów zrobić 12 list i po prostu na jednym adapterze wołać set
    private val mapOfAllMonths: Map<Month, FinanceAdapter> = initialiseMapOfMonths()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainListOfPayments.apply {
            adapter = getAdapterForCurrentMonth()
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.mainBalanceValueTextView.text = getAdapterForCurrentMonth()!!.sumAllValues().toString()

        val floatingActionButton: View = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener { _ ->
            startForResult.launch(Intent(this, EntryActivity::class.java))
        }
    }
// customView
    private fun getAdapterForMonth(month: Month): FinanceAdapter? {
        return mapOfAllMonths[month]
    }
    private fun getAdapterForCurrentMonth(): FinanceAdapter? {
        return mapOfAllMonths[LocalDate.now().month]
    }

    private fun onEditClicked(model: FinanceModel) {
        val editIntent = Intent(this, EntryActivity::class.java)
        editIntent.putExtra("ENTRY_TO_EDIT", model)
//        getAdapterForCurrentMonth()!!.removeRecentlyClickedEntry()
        startForResult.launch(editIntent)
    }

    private fun onRemoveAction(model: FinanceModel): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Delete position")
            .setMessage("Are you sure you want to delete this position?")
            .setPositiveButton(android.R.string.yes, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    getAdapterForCurrentMonth()!!.remove(model)
                    binding.mainBalanceValueTextView.text = getAdapterForCurrentMonth()!!.sumAllValues().toString()
                }
            })
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
        return true
    }

    override fun onResume() {
        super.onResume()
        binding.mainBalanceValueTextView.text = getAdapterForCurrentMonth()!!.sumAllValues().toString()
    }

//    private fun checkIfAlreadyExists(model: FinanceModel){
//        val x = mapOfAllMonths[model.date.month]
//        x.re
//    }

//    private fun checkMonthAndPut(model: FinanceModel) {
//        when (model.date.month) {
//            JANUARY -> mapOfAllMonths[JANUARY]?.add(model)
//            FEBRUARY -> mapOfAllMonths[FEBRUARY]?.add(model)
//            MARCH -> mapOfAllMonths[MARCH]?.add(model)
//            APRIL -> mapOfAllMonths[APRIL]?.add(model)
//            MAY -> mapOfAllMonths[MAY]?.add(model)
//            JUNE -> mapOfAllMonths[JUNE]?.add(model)
//            JULY -> mapOfAllMonths[JULY]?.add(model)
//            AUGUST -> mapOfAllMonths[AUGUST]?.add(model)
//            SEPTEMBER -> mapOfAllMonths[SEPTEMBER]?.add(model)
//            OCTOBER -> mapOfAllMonths[OCTOBER]?.add(model)
//            NOVEMBER -> mapOfAllMonths[NOVEMBER]?.add(model)
//            DECEMBER -> mapOfAllMonths[DECEMBER]?.add(model)
//            null -> mapOfAllMonths[JANUARY]?.add(model)
//        }
//    }

    private fun initialiseMapOfMonths(): Map<Month, FinanceAdapter> {
        return mapOf(
            JANUARY to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            FEBRUARY to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            MARCH to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            APRIL to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            MAY to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            JUNE to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            JULY to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            AUGUST to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            SEPTEMBER to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            OCTOBER to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            NOVEMBER to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
            DECEMBER to FinanceAdapter(this::onEditClicked, this::onRemoveAction)
        )
    }

}
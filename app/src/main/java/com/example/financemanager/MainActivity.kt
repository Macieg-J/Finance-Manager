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
import androidx.recyclerview.widget.RecyclerView
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
                checkMonthAndPut(result.data?.getParcelableExtra<FinanceModel>("ENTRY_INFO")!!)
            }
        }

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
// 2021-04-23
    private fun getAdapterForMonth(month: Month): FinanceAdapter? {
        return mapOfAllMonths[month]
    }
    private fun getAdapterForCurrentMonth(): FinanceAdapter? {
        return mapOfAllMonths[LocalDate.now().month]
    }

    private fun onEditClicked(model: FinanceModel) {
        val editIntent = Intent(this, EntryActivity::class.java)
        editIntent.putExtra("ENTRY_TO_EDIT", model)
        getAdapterForCurrentMonth()!!.removeRecentlyClickedEntry()
        startForResult.launch(editIntent)
    }

    private fun onRemoveAction(model: FinanceModel): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Delete position")
            .setMessage("Are you sure you want to delete this position?")
            .setPositiveButton(android.R.string.yes, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    getAdapterForCurrentMonth()!!.removeRecentlyClickedEntry()
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

    private fun checkMonthAndPut(model: FinanceModel) {
        when (model.date.month) {
            JANUARY -> mapOfAllMonths[JANUARY]?.add(model)
            FEBRUARY -> mapOfAllMonths[FEBRUARY]?.add(model)
            MARCH -> mapOfAllMonths[MARCH]?.add(model)
            APRIL -> mapOfAllMonths[APRIL]?.add(model)
            MAY -> mapOfAllMonths[MAY]?.add(model)
            JUNE -> mapOfAllMonths[JUNE]?.add(model)
            JULY -> mapOfAllMonths[JULY]?.add(model)
            AUGUST -> mapOfAllMonths[AUGUST]?.add(model)
            SEPTEMBER -> mapOfAllMonths[SEPTEMBER]?.add(model)
            OCTOBER -> mapOfAllMonths[OCTOBER]?.add(model)
            NOVEMBER -> mapOfAllMonths[NOVEMBER]?.add(model)
            DECEMBER -> mapOfAllMonths[DECEMBER]?.add(model)
//            Month.JANUARY -> listOfAllMonths[0]?.add(model)
//            Month.FEBRUARY -> listOfAllMonths[1]?.add(model)
//            Month.MARCH -> listOfAllMonths[2]?.add(model)
//            Month.APRIL -> listOfAllMonths[3]?.add(model)
//            Month.MAY -> listOfAllMonths[4]?.add(model)
//            Month.JUNE -> listOfAllMonths[5]?.add(model)
//            Month.JULY -> listOfAllMonths[6]?.add(model)
//            Month.AUGUST -> listOfAllMonths[7]?.add(model)
//            Month.SEPTEMBER -> listOfAllMonths[8]?.add(model)
//            Month.OCTOBER -> listOfAllMonths[9]?.add(model)
//            Month.NOVEMBER -> listOfAllMonths[10]?.add(model)
//            Month.DECEMBER -> listOfAllMonths[11]?.add(model)
        }
    }

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
//            1 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            2 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            3 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            4 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            5 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            6 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            7 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            8 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            9 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            10 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            11 to FinanceAdapter(this::onEditClicked, this::onRemoveAction),
//            12 to FinanceAdapter(this::onEditClicked, this::onRemoveAction)
        )
    }

}
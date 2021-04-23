package com.example.financemanager

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financemanager.adapter.FinanceAdapter
import com.example.financemanager.databinding.ActivityMainBinding
import com.example.financemanager.model.FinanceModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val financeAdapter by lazy { FinanceAdapter(this::onEditClicked, this::onRemoveAction) }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val x = result.data?.getParcelableExtra<FinanceModel>("ENTRY_INFO")
                financeAdapter.add(x!!)
                Log.d(TAG, financeAdapter.toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainListOfPayments.apply {
            adapter = financeAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.mainBalanceValueTextView.text = financeAdapter.sumAllValues().toString()

        val floatingActionButton: View = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener { _ ->
            startForResult.launch(Intent(this, EntryActivity::class.java))
        }
    }

    private fun onEditClicked(model: FinanceModel) {
        val editIntent = Intent(this, EntryActivity::class.java)
        editIntent.putExtra("ENTRY_TO_EDIT", model)
        financeAdapter.removeRecentlyClickedEntry()
        startForResult.launch(editIntent)
    }

    private fun onRemoveAction(model: FinanceModel): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Delete position")
            .setMessage("Are you sure you want to delete this position?")
            .setPositiveButton(android.R.string.yes, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    financeAdapter.removeRecentlyClickedEntry()
                    binding.mainBalanceValueTextView.text = financeAdapter.sumAllValues().toString()
                }
            })
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
        return true
    }

    override fun onResume() {
        super.onResume()
        binding.mainBalanceValueTextView.text = financeAdapter.sumAllValues().toString()
    }
}
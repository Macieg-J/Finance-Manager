package com.example.financemanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanager.databinding.ItemFinanceBinding
import com.example.financemanager.model.FinanceModel
import java.util.function.Consumer

class FinanceAdapter(
    private val editListener: (FinanceModel) -> Unit,
    private val deleteListener: (FinanceModel) -> Boolean
) :
    RecyclerView.Adapter<FinanceViewHolder>() {
    private val financeList = mutableListOf<FinanceModel>()
    private var clickedEntryPosition = Integer.MIN_VALUE


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
        val binding = ItemFinanceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FinanceViewHolder(binding).also { holder ->
            binding.root.setOnClickListener {
                clickedEntryPosition = holder.layoutPosition
                editListener(financeList[holder.layoutPosition])
            }
            binding.root.setOnLongClickListener { _ ->
                deleteListener(financeList[holder.layoutPosition])
            }
//                remove(holder.layoutPosition) } // maby snackbar
        }
    }

    override fun onBindViewHolder(holder: FinanceViewHolder, position: Int) {
        holder.bind(financeList[position])
    }

    override fun getItemCount(): Int = financeList.size

    fun add(financeModel: FinanceModel) {
        financeList.add(financeModel)
        notifyItemInserted(financeList.size - 1)
    }

    fun remove(position: Int): Boolean {
        if (position >= 0 && position < financeList.size) {
            financeList.removeAt(position)
            notifyItemRemoved(position)
            return true
        }
        return false
    }

    fun removeRecentlyClickedEntry() {
        if (clickedEntryPosition >= 0 && clickedEntryPosition < financeList.size) {
            financeList.removeAt(clickedEntryPosition)
            notifyItemRemoved(clickedEntryPosition)
        }
    }

    fun sumAllValues(): Double {
        var sumOfCosts = 0.0
        for (element in financeList) {
            sumOfCosts += element.cost.toDouble()
        }
        return sumOfCosts
    }
}
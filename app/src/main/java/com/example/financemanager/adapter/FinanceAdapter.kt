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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
        val binding = ItemFinanceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FinanceViewHolder(binding).also { holder ->
            binding.root.setOnClickListener {
                editListener(financeList[holder.layoutPosition])
            }
            binding.root.setOnLongClickListener { _ ->
                deleteListener(financeList[holder.layoutPosition])
            }
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

    fun replaceIfExists(financeModel: FinanceModel) {
        financeList.removeIf { it.id == financeModel.id }
        financeList.add(financeModel)
        notifyDataSetChanged()
    }

    fun remove(financeModel: FinanceModel){
        financeList.remove(financeModel)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = financeList[position].id.toLong()

    fun sumAllValues(): Double {
        var sumOfCosts = 0.0
        for (element in financeList) {
            sumOfCosts += element.cost.toDouble()
        }
        return sumOfCosts
    }
}
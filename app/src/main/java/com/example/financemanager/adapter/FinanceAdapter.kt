package com.example.financemanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanager.databinding.ItemFinanceBinding
import com.example.financemanager.model.FinanceModel

class FinanceAdapter : RecyclerView.Adapter<FinanceViewHolder>() {
    private val financeList = mutableListOf<FinanceModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
        val binding = ItemFinanceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FinanceViewHolder(binding).also { holder ->
            binding.root.setOnClickListener { remove(holder.layoutPosition) }
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

    fun remove(position: Int) {
        if (position >= 0 && position < financeList.size) {
            financeList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}
package com.example.financemanager.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.financemanager.databinding.ItemFinanceBinding
import com.example.financemanager.model.FinanceModel

class FinanceViewHolder(
    private val layoutBinding: ItemFinanceBinding
) : RecyclerView.ViewHolder(layoutBinding.root) {

    fun bind(finance: FinanceModel) = with(layoutBinding) {
        itemFinanceIcon.setImageBitmap(finance.photo)
        itemFinanceProductCategory.text = finance.category
        itemFinancePlace.text = finance.place
        itemFinanceProductCost.text = finance.cost
        itemFinanceDate.text = finance.date.toString()
    }
}
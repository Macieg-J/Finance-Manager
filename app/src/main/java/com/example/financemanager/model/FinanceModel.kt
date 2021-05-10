package com.example.financemanager.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class FinanceModel(
    var id: String,
    val image: Bitmap,
    val category: String,
    val place: String,
    val cost: String,
    val date: LocalDate
) : Parcelable

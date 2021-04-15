package com.example.financemanager.model

import android.graphics.Bitmap
import java.time.LocalDate

data class FinanceModel(
    val photo: Bitmap,
    val category : String,
    val place: String,
    val cost: String,
    val date: String // todo change to date (LocalDate?)
)

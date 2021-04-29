package com.example.financemanager.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
@Parcelize
data class FinanceModel(
    val image: Bitmap,
    val category : String,
    val place: String,
    val cost: String,
    val date: LocalDate // todo change to date (LocalDate?)
) : Parcelable

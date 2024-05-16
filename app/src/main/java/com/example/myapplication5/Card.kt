package com.example.myapplication5

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    @DrawableRes val imageResId: Int,
    val title: String,
    val subtitle: String,
    val question: String,
    val hint: String,
    val answer: String,
    val translation: String,
    val description: String
) : Parcelable

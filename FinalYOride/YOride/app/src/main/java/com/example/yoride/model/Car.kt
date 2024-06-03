package com.example.yoride.model
import androidx.annotation.DrawableRes

data class Car(
    val id:String,
    val brand: String,
    val model: String,
    val rating: Double,
    val rentalPrice: String,
    @DrawableRes val imageResId: Int
)
package com.example.yoride.model


import androidx.compose.ui.graphics.painter.Painter

data class MenuItem(
    val id: Int,
    val title: String,
    val imageResource: Painter
)
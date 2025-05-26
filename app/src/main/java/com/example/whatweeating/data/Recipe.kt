package com.example.whatweeating.data

import androidx.compose.ui.graphics.painter.Painter

data class Recipe(
    val name: String,
    val image: Painter,
    val rating: Double,
    val difficulty: String,
    val time: String
)
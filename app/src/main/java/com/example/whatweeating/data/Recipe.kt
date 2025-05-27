package com.example.whatweeating.data

import androidx.compose.ui.graphics.painter.Painter

data class Recipe(
    val id: Int,
    val name: String,
    val image: Painter,
    val rating: Double,
    val numberOfRatings: Int,
    val difficulty: String,
    val time: String,
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: List<String> = emptyList(),
    val youtubeUrl: String? = null,
    val description: String
)
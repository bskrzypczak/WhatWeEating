package com.example.whatweeating.data

data class RecipeSearchResponse (
    val results: List<RecipeAPI>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
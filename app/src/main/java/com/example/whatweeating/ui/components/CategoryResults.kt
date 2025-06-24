package com.example.whatweeating.ui.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatweeating.ui.viewmodels.SharedViewModel

@Composable
fun CategoryResults(navController: NavController, viewModel: SharedViewModel, category: String) {

    // Fetchujemy dane i logujemy zmianę kategorii
    LaunchedEffect(category) {
        Log.d("KATEGORIE", "Wybrana kategoria: $category")
        viewModel.searchByCategory(category)
    }

    val recipes = viewModel.recipesByCategory

    Text(
        text = "Wyniki dla: $category, znaleziono: ${recipes.size}",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleMedium
    )

    // Tu wrzuć np. LazyColumn z listą przepisów
}

package com.example.whatweeating.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.whatweeating.data.Recipe

class SharedViewModel : ViewModel() {
    var selectedRecipe = mutableStateOf<Recipe?>(null)
        private set

    fun selectRecipe(recipe: Recipe) {
        selectedRecipe.value = recipe
    }
}
package com.example.whatweeating.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatweeating.BuildConfig
import com.example.whatweeating.data.Recipe
import com.example.whatweeating.data.RecipeAPI
import com.example.whatweeating.data.RecipeDetails
import com.example.whatweeating.network.RetrofitClient
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class SharedViewModel : ViewModel() {

    var recipes by mutableStateOf<List<RecipeAPI>>(emptyList())
    var recipesByCategory by mutableStateOf<List<RecipeAPI>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    val recipeDetails = mutableStateOf<RecipeDetails?>(null)

    var selectedRecipe = mutableStateOf<Recipe?>(null)
    var selectedRecipe2 = mutableStateOf<RecipeAPI?>(null)
        private set

    private val _selectedCategory = mutableStateOf<String?>(null)
    val selectedCategory: State<String?> = _selectedCategory

    fun selectCategory(category: String?) {
        _selectedCategory.value = category
    }

    fun selectRecipe(recipe: Recipe) {
        selectedRecipe.value = recipe
    }

    fun selectRecipeApi(recipe: RecipeAPI){
        selectedRecipe2.value = recipe
    }

    fun searchRecipes(query: String){
        isLoading = true
        error = null
        viewModelScope.launch {
            try{
                val apiKey = BuildConfig.API_KEY
                val response = RetrofitClient.apiService.searchRecipes(query, apiKey, 20)
                recipes = response.results
                Log.d("API_SUCCESS", "Znaleziono ${recipes.size} przepisów")
                recipes.forEach { Log.d("API_RECIPE", it.title) }
            } catch (e: Exception) {
                error = e.message
                Log.e("API_ERROR", "Błąd podczas pobierania przepisów: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun searchByCategory(category: String){
        isLoading = true
        error = null
        viewModelScope.launch {
            try{
                val apiKey = BuildConfig.API_KEY
                val response = RetrofitClient.apiService.searchByCategory(category, apiKey, 10)
                recipesByCategory = response.results
                Log.d("API_SUCCESS", "Znaleziono ${recipesByCategory.size} przepisów")
                recipesByCategory.forEach { Log.d("API_RECIPE", it.title) }
            } catch (e: Exception) {
                error = e.message
                Log.e("API_ERROR", "Błąd podczas pobierania przepisów: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun getRecipeDetails(id: Int) {
        isLoading = true
        error = null
        viewModelScope.launch {
            try {
                val apiKey = BuildConfig.API_KEY
                val response = RetrofitClient.apiService.getRecipeDetails(id, apiKey)
                recipeDetails.value = response
                Log.d("API_SUCCESS", "Pobrano szczegóły przepisu: ${response.title}")
            } catch (e: Exception) {
                error = e.message
                Log.e("API_ERROR", "Błąd podczas pobierania szczegółów: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}

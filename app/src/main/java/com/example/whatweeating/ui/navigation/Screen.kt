package com.example.whatweeating.ui.navigation

sealed class Screen(val route: String, val label: String) {
    object HomeScreen : Screen("home_screen", "Strona główna")
    object FavoritesScreen : Screen("favorites_screen", "Ulubione")
    object CookingScreen : Screen("cooking_screen", "Gotuj")
    object CommunityScreen : Screen("community_screen", "Społeczność")
    object ProfileScreen : Screen("profile_screen", "Mój profil")
    object RecipeScreen : Screen("recipe_screen", "Przepis")
}
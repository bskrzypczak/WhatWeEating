package com.example.whatweeating.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.whatweeating.ui.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String
) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.FavoritesScreen,
        Screen.CookingScreen,
        Screen.CommunityScreen,
        Screen.ProfileScreen
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    Icon(imageVector = when (screen) {
                        Screen.HomeScreen -> Icons.Rounded.Home
                        Screen.FavoritesScreen -> Icons.Rounded.Favorite
                        Screen.CookingScreen -> Icons.Rounded.Kitchen
                        Screen.CommunityScreen -> Icons.Rounded.People
                        Screen.ProfileScreen -> Icons.Rounded.Person
                    }, contentDescription = screen.route)
                },
                label = { Text(screen.route.capitalize()) }
            )
        }
    }
}

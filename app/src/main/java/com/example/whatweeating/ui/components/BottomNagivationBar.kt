package com.example.whatweeating.ui.components

import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavHostController
import com.example.whatweeating.data.BottomNavigation
import com.example.whatweeating.ui.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String
) {
    val items = listOf(
        Screen.Home,
        Screen.Favorites,
        Screen.Cooking,
        Screen.Community,
        Screen.Profile
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = when (screen) {
                        Screen.Home -> Icons.Rounded.Home
                        Screen.Favorites -> Icons.Rounded.Favorite
                        Screen.Cooking -> Icons.Rounded.Kitchen
                        Screen.Community -> Icons.Rounded.People
                        Screen.Profile -> Icons.Rounded.Person
                    }, contentDescription = screen.route)
                },
                label = { Text(screen.route.capitalize()) }
            )
        }
    }
}

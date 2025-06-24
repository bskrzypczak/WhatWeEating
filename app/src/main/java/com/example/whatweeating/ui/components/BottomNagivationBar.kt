package com.example.whatweeating.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Abc
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .height(90.dp)
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            Screen.HomeScreen -> Icons.Rounded.Home
                            Screen.FavoritesScreen -> Icons.Rounded.Favorite
                            Screen.CookingScreen -> Icons.Rounded.RestaurantMenu
                            Screen.CommunityScreen -> Icons.Rounded.People
                            Screen.ProfileScreen -> Icons.Rounded.Person
                            Screen.RecipeScreen -> Icons.Rounded.Abc
                            Screen.LogInScreen -> Icons.Rounded.Abc
                            Screen.SignUpScreen -> Icons.Rounded.Abc
                            Screen.ResultsScreen -> Icons.Rounded.Abc
                            Screen.TestScreen -> Icons.Rounded.Abc
                        },
                        contentDescription = screen.route,
                        modifier = Modifier.size(26.dp),
                        tint = MaterialTheme.colorScheme.primary

                    )
                },
                label = {
                    Text(
                        text = screen.label.replaceFirstChar { it.uppercaseChar() },
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 1.dp),
                        color = MaterialTheme.colorScheme.primary

                    )
                },
            )
        }
    }

}

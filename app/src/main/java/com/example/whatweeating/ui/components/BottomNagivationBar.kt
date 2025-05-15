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
import com.example.whatweeating.data.BottomNavigation

val items = listOf(
    BottomNavigation(
        title = "Strona główna",
        icon = Icons.Rounded.Home
    ),

    BottomNavigation(
        title = "Ulubione",
        icon = Icons.Rounded.Favorite
    ),

    BottomNavigation(
        title = "...",
        icon = Icons.Rounded.Kitchen
    ),

    BottomNavigation(
        title = "Społeczność",
        icon = Icons.Rounded.People
    ),

    BottomNavigation(
        title = "Profil",
        icon = Icons.Rounded.Person
    ),
)

@Composable
fun BottomNavigationBar(){
    NavigationBar {
        Row(){
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == 0,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}
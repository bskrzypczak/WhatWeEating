package com.example.whatweeating.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatweeating.data.CategoryIcon
import com.example.whatweeating.R

@Composable
fun CategoriesList(){
    val categories = listOf(
        CategoryIcon(
            title = "Pizza",
            image = painterResource(id = R.drawable.pizza)
        ),
        CategoryIcon(
            title = "Azjatyckie",
            image = painterResource(id = R.drawable.azjatyckie)
        ),
        CategoryIcon(
            title = "Burgery",
            image = painterResource(id = R.drawable.burger)
        ),
        CategoryIcon(
            title = "Makarony",
            image = painterResource(id = R.drawable.pasta)
        ),
        CategoryIcon(
            title = "Meksykańskie",
            image = painterResource(id = R.drawable.mexican)
        ),
        CategoryIcon(
            title = "Wegańskie",
            image = painterResource(id = R.drawable.vegan)
        ),
        CategoryIcon(
            title = "Bezglutenowe",
            image = painterResource(id = R.drawable.glutenfree)
        ),
        CategoryIcon(
            title = "Desery",
            image = painterResource(id = R.drawable.desery)
        )
    )
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategories.contains(category.title)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable {
                        selectedCategories = if (isSelected)
                            selectedCategories - category.title
                        else
                            selectedCategories + category.title
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = category.image,
                        contentDescription = category.title,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = category.title,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}
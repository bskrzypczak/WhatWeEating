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
fun CategoriesList(
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit
) {
    val categories = listOf(
        CategoryIcon("Italian", painterResource(id = R.drawable.pizza)),
        CategoryIcon("Azjatyckie", painterResource(id = R.drawable.azjatyckie)),
        CategoryIcon("Burgery", painterResource(id = R.drawable.burger)),
        CategoryIcon("Makarony", painterResource(id = R.drawable.pasta)),
        CategoryIcon("Meksykańskie", painterResource(id = R.drawable.mexican)),
        CategoryIcon("Wegańskie", painterResource(id = R.drawable.vegan)),
        CategoryIcon("Bezglutenowe", painterResource(id = R.drawable.glutenfree)),
        CategoryIcon("Desery", painterResource(id = R.drawable.desery))
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategory == category.title

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    val newSelection = if (isSelected) null else category.title
                    onCategorySelected(newSelection)
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

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = category.title,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

package com.example.whatweeating.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import com.example.whatweeating.data.CategoryIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.RamenDining

val categories = listOf(
    CategoryIcon(
        title = "Fast Food",
        icon = Icons.Rounded.Fastfood
    ),

    CategoryIcon(
        title = "Azjatyckie",
        icon = Icons.Rounded.RamenDining
    )
)

@Composable
fun CategoriesPanel(
    selectedCategory: String? = null,
    onCategorySelected: (String) -> Unit = {}
) {
    var currentSelection by remember { mutableStateOf(selectedCategory) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        categories.forEach { category ->
            val isSelected = currentSelection == category.title
            FilterChip(
                selected = isSelected,
                onClick = {
                    currentSelection = category.title
                    onCategorySelected(category.title)
                },
                label = {
                    Text(
                        text = category.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.title
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                    labelColor = if (isSelected) Color.White else Color.Black,
                    iconColor = if (isSelected) Color.White else Color.DarkGray
                )
            )
        }
    }
}

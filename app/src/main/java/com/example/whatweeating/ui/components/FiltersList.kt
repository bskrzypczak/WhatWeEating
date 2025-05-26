package com.example.whatweeating.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatweeating.data.FoodFilter

@Composable
fun FiltersList() {
    val filters = listOf(
        FoodFilter(
            name = "Poziom",
            icon = Icons.Outlined.Star,
            options = listOf("Łatwy", "Średni", "Trudny")
        ),
        FoodFilter(
            name = "Czas",
            icon = Icons.Outlined.Timer,
            options = listOf("Do 15 min", "15–30 min", "30+ min")
        )
    )

    var expandedFilter by remember { mutableStateOf<String?>(null) }
    var selectedOptions by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filters) { filter ->
            val selectedOption = selectedOptions[filter.name]
            val isSelected = selectedOption != null
            val label = selectedOption ?: filter.name

            Box {
                FilterChip(
                    icon = filter.icon,
                    label = label,
                    isSelected = isSelected,
                    onClick = {
                        when {
                            expandedFilter == filter.name -> expandedFilter = null
                            isSelected -> {
                                // resetuj zaznaczenie jeśli kliknięto wybrany filtr
                                selectedOptions = selectedOptions - filter.name
                                expandedFilter = null
                            }
                            else -> expandedFilter = filter.name
                        }
                    }
                )

                DropdownMenu(
                    expanded = expandedFilter == filter.name,
                    onDismissRequest = { expandedFilter = null }
                ) {
                    filter.options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOptions = selectedOptions + (filter.name to option)
                                expandedFilter = null
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Black else Color(0xFFF0F0F0)
    val contentColor = if (isSelected) Color.White else Color.Black

    Surface(
        shape = RoundedCornerShape(50),
        color = backgroundColor,
        tonalElevation = 1.dp,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                color = contentColor
            )
        }
    }
}

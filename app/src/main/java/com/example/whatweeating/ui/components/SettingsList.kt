package com.example.whatweeating.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.FontDownload
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whatweeating.data.SettingField

val settings = listOf(
    SettingField(
        name = "Tryb ciemny",
        icon = Icons.Rounded.DarkMode
    ),
    SettingField(
        name = "Powiadomienia",
        icon = Icons.Rounded.Notifications
    ),
    SettingField(
        name = "Język",
        icon = Icons.Rounded.Language
    ),
    SettingField(
        name = "Rozmiar tekstu",
        icon = Icons.Rounded.FontDownload
    )
)

@Composable
fun SettingsList(
    onSettingClick: (SettingField) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        settings.forEach { setting ->
            SettingItem(setting = setting, onClick = { onSettingClick(setting) })
        }
    }
}

@Composable
fun SettingItem(setting: SettingField, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = setting.icon,
            contentDescription = setting.name,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = setting.name)
    }
}

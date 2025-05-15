package com.example.whatweeating.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingHeader(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = "Cześć, $name!",
            style = TextStyle(fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.width(140.dp))

        IconButton(onClick = {
            Log.d("App", "Przejdz do ustawien")
        }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Ustawienia",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
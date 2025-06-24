package com.example.whatweeating.ui.screens

import com.example.whatweeating.ui.viewmodels.AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatweeating.ui.viewmodels.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    val usersCollection = FirebaseFirestore.getInstance().collection("users")

    LaunchedEffect(authState.value) {
        when (val state = authState.value) {
            is AuthState.Authenticated -> {
                val uid = FirebaseAuth.getInstance().currentUser?.uid

                if (uid != null) {
                    val userData = mapOf(
                        "email" to email,
                        "name" to name
                    )

                    usersCollection.document(uid).set(userData)
                        .addOnSuccessListener {
                            navController.navigate("home_screen")
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Błąd zapisu danych", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "Nie udało się pobrać UID", Toast.LENGTH_SHORT).show()
                }
            }

            is AuthState.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Zarejestruj się", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Imię") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Hasło") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                val trimmedName = name.trim()
                val nameRegex = Regex("^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]{3,}$")

                when {
                    trimmedName.isEmpty() -> {
                        Toast.makeText(context, "Imię nie może być puste", Toast.LENGTH_SHORT).show()
                    }
                    !nameRegex.matches(trimmedName) -> {
                        Toast.makeText(context, "Imię musi mieć min. 3 litery i zawierać tylko litery", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        authViewModel.signup(email, password, trimmedName)
                    }
                }
            },
            enabled = authState.value != AuthState.Loading
        ) {
            Text("Utwórz konto")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("login_screen")
        }) {
            Text("Masz już konto? Zaloguj się")
        }

        TextButton(onClick = {
            navController.navigate("home_screen")
        }) {
            Text("Chcę korzystać bez konta")
        }
    }
}

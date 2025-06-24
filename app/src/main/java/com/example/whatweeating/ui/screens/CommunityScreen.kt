package com.example.whatweeating.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.viewmodels.AuthState
import com.example.whatweeating.ui.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

data class Friend(val uid: String, val name: String, val email: String)

@Composable
fun CommunityScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()
    val currentRoute = "community_screen"
    val userUid = FirebaseAuth.getInstance().currentUser?.uid

    var friends by remember { mutableStateOf<List<Friend>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    var emailInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun loadFriends() {
        if (userUid == null) return
        loading = true
        Log.d("CommunityScreen", "Ładowanie znajomych...")

        FirebaseFirestore.getInstance()
            .collection("users")
            .document(userUid)
            .collection("friends")
            .get()
            .addOnSuccessListener { snapshot ->
                val friendUids = snapshot.documents.mapNotNull { it.id }
                Log.d("CommunityScreen", "Znaleziono UIDy znajomych: $friendUids")

                if (friendUids.isNotEmpty()) {
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .whereIn(FieldPath.documentId(), friendUids)
                        .get()
                        .addOnSuccessListener { docs ->
                            friends = docs.map {
                                Friend(
                                    uid = it.id,
                                    name = it.getString("name") ?: "Nieznajomy",
                                    email = it.getString("email") ?: ""
                                )
                            }
                            Log.d("CommunityScreen", "Załadowano znajomych: $friends")
                            loading = false
                        }
                        .addOnFailureListener {
                            Log.e("CommunityScreen", "Błąd przy pobieraniu danych znajomych: ${it.message}")
                            loading = false
                        }
                } else {
                    Log.d("CommunityScreen", "Brak znajomych.")
                    friends = emptyList()
                    loading = false
                }
            }
            .addOnFailureListener {
                Log.e("CommunityScreen", "Błąd przy pobieraniu UIDów znajomych: ${it.message}")
                loading = false
            }
    }


    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated && userUid != null) {
            loadFriends()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = currentRoute)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (authState) {
                is AuthState.Authenticated -> {
                    if (loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    } else if (friends.isEmpty()) {
                        Text(
                            "Nie masz jeszcze znajomych.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                            items(friends) { friend ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(friend.name, fontWeight = FontWeight.Bold)
                                        Text(friend.email, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Dodaj znajomego")
                    }


                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDialog = false
                                emailInput = ""
                                errorMessage = null
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    addFriendByEmail(
                                        email = emailInput.trim(),
                                        currentUserUid = userUid!!,
                                        onSuccess = {
                                            showDialog = false
                                            emailInput = ""
                                            errorMessage = null
                                            loadFriends() // odświeżenie listy
                                        },
                                        onFailure = {
                                            errorMessage = it
                                        }
                                    )
                                }) {
                                    Text("Dodaj")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    showDialog = false
                                    errorMessage = null
                                }) {
                                    Text("Anuluj")
                                }
                            },
                            title = { Text("Dodaj znajomego") },
                            text = {
                                Column {
                                    Text("Podaj adres e-mail znajomego:")
                                    TextField(
                                        value = emailInput,
                                        onValueChange = { emailInput = it },
                                        singleLine = true,
                                        placeholder = { Text("email@example.com") }
                                    )
                                    if (errorMessage != null) {
                                        Text(
                                            errorMessage!!,
                                            color = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }
                }

                is AuthState.Unauthenticated -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 128.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Ikona konta",
                            modifier = Modifier
                                .size(222.dp)
                                .padding(bottom = 24.dp)
                        )
                        Button(
                            onClick = { navController.navigate("login_screen") },
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {
                            Icon(Icons.Default.Login, contentDescription = "Zaloguj się")
                            Text(
                                text = "Zaloguj się, żeby zobaczyć listę znajomych",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                is AuthState.Loading -> Text("Ładowanie...")
                is AuthState.Error -> Text("Błąd: ${(authState as AuthState.Error).message}")
                null -> Text("Sprawdzanie statusu logowania...")
            }
        }
    }
}

fun addFriendByEmail(
    email: String,
    currentUserUid: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    Log.d("CommunityScreen", "Szukam użytkownika o emailu: $email")

    db.collection("users")
        .whereEqualTo("email", email)
        .get()
        .addOnSuccessListener { result ->
            if (result.isEmpty) {
                Log.w("CommunityScreen", "Nie znaleziono użytkownika o tym adresie e-mail.")
                onFailure("Nie znaleziono użytkownika o tym adresie e-mail.")
            } else {
                val friendDoc = result.documents.first()
                val friendUid = friendDoc.id
                Log.d("CommunityScreen", "Znaleziono UID znajomego: $friendUid")

                if (friendUid == currentUserUid) {
                    Log.w("CommunityScreen", "Użytkownik próbował dodać siebie.")
                    onFailure("Nie możesz dodać siebie jako znajomego.")
                    return@addOnSuccessListener
                }

                db.collection("users")
                    .document(currentUserUid)
                    .collection("friends")
                    .document(friendUid)
                    .set(mapOf("addedAt" to System.currentTimeMillis()))
                    .addOnSuccessListener {
                        Log.d("CommunityScreen", "Dodano znajomego do Firestore.")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.e("CommunityScreen", "Błąd przy dodawaniu znajomego: ${it.message}")
                        onFailure("Błąd podczas dodawania znajomego.")
                    }
            }
        }
        .addOnFailureListener {
            Log.e("CommunityScreen", "Błąd przy wyszukiwaniu użytkownika: ${it.message}")
            onFailure("Wystąpił błąd przy wyszukiwaniu użytkownika.")
        }
}

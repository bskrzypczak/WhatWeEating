package com.example.whatweeating.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AuthViewModel : ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    val currentUserEmail: String?
        get() = auth.currentUser?.email

    val currentUserUid: String?
        get() = auth.currentUser?.uid

    private val db = FirebaseFirestore.getInstance()
    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName


    init {
        checkAuthStatus()
    }

    fun fetchUserName() {
        val uid = auth.currentUser?.uid ?: run {
            _userName.value = null
            return
        }

        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    _userName.value = document.getString("name")
                } else {
                    _userName.value = null
                }
            }
            .addOnFailureListener {
                _userName.value = null
            }
    }


    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email : String,password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email i hasło nie mogą być puste!")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                    fetchUserName()
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Coś poszło nie tak")
                }
            }
    }

    fun signup(email: String, password: String, name: String) {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            _authState.value = AuthState.Error("Wszystkie pola muszą być wypełnione")
            return
        }

        val nameRegex = Regex("^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]{3,}$")
        if (!nameRegex.matches(name.trim())) {
            _authState.value = AuthState.Error("Imię musi mieć min. 3 litery i zawierać tylko litery")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    val user = hashMapOf(
                        "email" to email,
                        "name" to name.trim()
                    )
                    fetchUserName()

                    if (uid != null) {
                        FirebaseFirestore.getInstance()
                            .collection("users")
                            .document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                _authState.value = AuthState.Authenticated
                            }
                            .addOnFailureListener { e ->
                                _authState.value = AuthState.Error("Błąd zapisu danych: ${e.message}")
                            }
                    } else {
                        _authState.value = AuthState.Error("Nie udało się uzyskać UID użytkownika")
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Coś poszło nie tak")
                }
            }
    }


    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


}


sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}
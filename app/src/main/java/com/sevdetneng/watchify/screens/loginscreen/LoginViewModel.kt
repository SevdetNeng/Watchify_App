package com.sevdetneng.watchify.screens.loginscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    val firebaseAuth = Firebase.auth
    fun createUserWithEmailAndPassword(
        email: String, password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    onSuccess()
                }.addOnFailureListener{
                    Log.d("FBExc", it.message.toString())
                    onFailure()
                }
        } catch (e: FirebaseAuthException) {
            Log.d("FBExc", e.message.toString())

        }
    }

    fun loginWithEmailAndPassword(
        email: String, password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener{
                    Log.d("FBExc", it.message.toString())
                    onFailure()
                }

        } catch (e: FirebaseAuthException) {
            Log.d("FBExc", e.message.toString())

        }
    }
}

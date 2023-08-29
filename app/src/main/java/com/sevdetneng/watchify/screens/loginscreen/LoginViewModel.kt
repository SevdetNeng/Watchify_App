package com.sevdetneng.watchify.screens.loginscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.model.firebase.FbUser
import com.sevdetneng.watchify.utils.Constants.USERS_PATH

class LoginViewModel : ViewModel() {
    val firebaseAuth = Firebase.auth
    fun createUserWithEmailAndPassword(
        email: String, password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val fbUser = FbUser(userId = result.user?.uid,
                        email = result.user?.email,
                        displayName = result.user?.email?.trim()?.substringBefore("@"),
                        photoUrl = "",
                        country = "",
                        description = ""
                        ).toMap()
                    FirebaseFirestore.getInstance().collection(USERS_PATH).add(fbUser)
                    onSuccess()
                }.addOnFailureListener{
                    Log.d("FBExc", it.message.toString())
                    onFailure(it.message.toString())
                }
        } catch (e: FirebaseAuthException) {
            Log.d("FBExc", e.message.toString())

        }
    }

    fun loginWithEmailAndPassword(
        email: String, password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener{
                    Log.d("FBExc", it.message.toString())
                    onFailure(it.message.toString())
                }

        } catch (e: FirebaseAuthException) {
            Log.d("FBExc", e.message.toString())

        }
    }
}

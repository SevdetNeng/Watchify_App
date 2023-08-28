package com.sevdetneng.watchify.screens.loginscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sevdetneng.watchify.components.FormInput

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController,
                loginViewModel: LoginViewModel = viewModel()) {
    val emailValue: MutableState<String> = remember { mutableStateOf("") }
    val passwordValue: MutableState<String> = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLoginState: MutableState<Boolean> = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 48.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Watchify", color = Color.Green.copy(0.7f),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.height(48.dp))
        FormInput(
            value = emailValue, label = "Email", isPassword = false,
            onAction = KeyboardActions {
                keyboardController!!.hide()
            },
            modifier = Modifier.fillMaxWidth()
        )
        FormInput(
            value = passwordValue, label = "Password", isPassword = true,
            onAction = KeyboardActions {
                keyboardController!!.hide()
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (isLoginState.value) {
                    loginViewModel.loginWithEmailAndPassword(emailValue.value,passwordValue.value,
                        onSuccess = {
                            Log.d("Login","Success")
                        },
                        onFailure = {
                            Log.d("Login","Failure")
                        }
                        )
                }else{
                    loginViewModel.createUserWithEmailAndPassword(emailValue.value,
                        passwordValue.value,
                        onSuccess = {
                            Log.d("CreateUser","Success")
                        }, onFailure = {
                            Log.d("CreateUser","Failure")
                        } )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            shape = RectangleShape
        ) {
            Text(text = if (isLoginState.value) "Login" else "Create Account")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isLoginState.value) "Don't have an account? " else "Already have an account? ",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = if (isLoginState.value) "Sign-Up" else "Login",
                color = Color.Green.copy(1f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    isLoginState.value = !isLoginState.value
                })
        }

    }
}
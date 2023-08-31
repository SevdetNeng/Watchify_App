package com.sevdetneng.watchify.screens.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sevdetneng.watchify.components.WatchifyBottomBar

@Composable
fun SearchScreen(navController: NavController) {
    val searchValueState : MutableState<String> = remember{mutableStateOf("")}
    Scaffold(bottomBar = { WatchifyBottomBar(navController = navController,
        screen = "Search") }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
            .fillMaxSize()){
            SearchBar(value = searchValueState, label = "Search")
        }
    }

}

@Composable
fun SearchBar(value : MutableState<String>,
              imeAction: ImeAction = ImeAction.Done,
              onAction : KeyboardActions = KeyboardActions.Default,
              keyboardType: KeyboardType = KeyboardType.Text,
              onValueChange : (String) -> Unit = {},
              label : String){
    OutlinedTextField(value = value.value, onValueChange = {},
        label = {Text(label)},
        keyboardOptions = KeyboardOptions(imeAction = imeAction,
            keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()


    )
}
package com.sevdetneng.watchify.screens.userscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.components.WatchifyBottomBar
import com.sevdetneng.watchify.navigation.Screens

@Composable
fun UserScreen(navController: NavController,
               userViewModel: UserViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = userViewModel.emailValue.value){
        userViewModel.getUserData()
    }
    val isEditingState = userViewModel.isEditingState.value
    val displayNameValue = userViewModel.displayNameValue.value
    val photoUrlValue = userViewModel.photoUrlValue.value
    val countryValue = userViewModel.countryValue.value
    val descriptionValue = userViewModel.descriptionValue.value
    val emailValue = userViewModel.emailValue.value

    Scaffold(bottomBar = { WatchifyBottomBar(navController = navController, "User") },
        containerColor = Color(0xff111820))
    { padding->
        Card(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = padding.calculateBottomPadding()),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally){

                Row(horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()){
                    Icon(Icons.Default.Edit,"Edit",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                userViewModel.isEditingState.value =
                                    !userViewModel.isEditingState.value
                            },
                        tint = if(isEditingState) Color(0xff47a3d0)
                    else Color.Gray)
                }

                if(photoUrlValue!="" && photoUrlValue!="null"){
                    Surface(modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xff47a3d0), CircleShape),
                        color = Color.Transparent){
                        AsyncImage(model = photoUrlValue, contentDescription = "User Image",
                            contentScale = ContentScale.FillBounds)
                    }

                }else{
                    Surface(modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xff47a3d0), CircleShape),
                        color = Color.Transparent){
                        AsyncImage(model = "https://icons.veryicon.com/png/o/internet--web/prejudice/user-128.png", contentDescription = "User Image",
                            contentScale = ContentScale.FillBounds)
                    }
                }

                UserTextField(label = "Email", value = emailValue , onValueChange = { userViewModel.emailValue.value = it }, enabled = false)
                UserTextField(label = "Display Name", value = displayNameValue , onValueChange = { userViewModel.displayNameValue.value = it }, enabled = isEditingState)
                UserTextField(label = "Country", value = countryValue , onValueChange = { userViewModel.countryValue.value = it }, enabled = isEditingState)
                UserTextField(label = "Photo URL", value = photoUrlValue , onValueChange = { userViewModel.photoUrlValue.value = it }, enabled = isEditingState)
                UserTextField(label = "Description", value = descriptionValue , onValueChange = { userViewModel.descriptionValue.value = it }, enabled = isEditingState)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center){
                    Button(onClick = { Firebase.auth.signOut()
                        navController.navigate(Screens.LoginScreen.name)
                    }) {
                        Text("Logout")
                    }
                    Spacer(Modifier.width(16.dp))
                    AnimatedVisibility(visible = isEditingState) {
                        Button(onClick = { userViewModel.updateUserData()
                            userViewModel.isEditingState.value = false}) {
                            Text("Update")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun UserTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean
){
    OutlinedTextField(value = value, onValueChange = { onValueChange(it) },
        enabled = enabled,
        keyboardActions = onAction,
        label = {Text(label)},
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(focusedLabelColor = Color(0xff47a3d0),
            focusedIndicatorColor = Color(0xff47a3d0),
            unfocusedLabelColor = Color.Gray,
            disabledLabelColor = Color.Gray),
        maxLines = if(label == "Description") 3 else 1,
        leadingIcon = {
            when (label) {
                "Email" -> {
                    Icon(Icons.Outlined.Email,"Email",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Black)
                }

                "Display Name" -> {
                    Icon(Icons.Outlined.Person,"Email",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Black)
                }

                "Country" -> {
                    Icon(Icons.Outlined.Flag,"Email",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Black)
                }

                "Photo URL" -> {
                    Icon(Icons.Outlined.Photo,"Email",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Black)
                }

                else -> Icon(Icons.Outlined.Description,"Email",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black)
            }
        }
    )
}
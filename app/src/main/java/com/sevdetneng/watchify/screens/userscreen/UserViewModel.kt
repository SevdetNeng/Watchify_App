package com.sevdetneng.watchify.screens.userscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sevdetneng.watchify.model.firebase.FbUser
import com.sevdetneng.watchify.repository.FbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val repository: FbRepository) : ViewModel() {

    val isEditingState = mutableStateOf(false)
    val displayNameValue = mutableStateOf("")
    val photoUrlValue = mutableStateOf("")
    val countryValue = mutableStateOf("")
    val descriptionValue = mutableStateOf("")
    val emailValue = mutableStateOf("")
    val isLoading = mutableStateOf(true)
    val user : MutableState<FbUser> = mutableStateOf(FbUser())
    fun getUserData(){
        viewModelScope.launch {
            isLoading.value = true
            try{
                user.value = repository.getUserData(Firebase.auth.currentUser!!.uid)
                if(user.value.userId!=null){
                    displayNameValue.value = user.value.displayName.toString()
                    photoUrlValue.value = user.value.photoUrl.toString()
                    countryValue.value = user.value.country.toString()
                    descriptionValue.value = user.value.description.toString()
                    emailValue.value = user.value.email.toString()
                }
                isLoading.value = false
            }catch (e : Exception){
                isLoading.value = false
                e.printStackTrace()
            }
        }
    }

    fun updateUserData(){
        val newUserData = FbUser(displayName = displayNameValue.value,
            photoUrl = photoUrlValue.value,
            country = countryValue.value,
            email = emailValue.value,
            description = descriptionValue.value,
            userId = Firebase.auth.uid
            )
        repository.updateUserData(newUserData)
    }
}
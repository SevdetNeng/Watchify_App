package com.sevdetneng.watchify.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    modifier: Modifier = Modifier,
    value: MutableState<String> = mutableStateOf("Value"),
    label: String = "Input",
    imeAction: ImeAction = ImeAction.Next,
    enabled: Boolean = true,
    isPassword : Boolean,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(value = value.value, onValueChange = { newValue ->
        value.value = newValue
    },
        label = {Text(label)},
        keyboardOptions = KeyboardOptions(imeAction = imeAction,
            keyboardType = if(isPassword) KeyboardType.Password else KeyboardType.Email),
        enabled = enabled,
        maxLines = 1,
        modifier = modifier.padding(start = 10.dp,end = 10.dp,bottom = 10.dp),
        keyboardActions = onAction,
        visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(focusedLabelColor = Color.Green.copy(0.7f),
            focusedIndicatorColor = Color.Green.copy(0.7f)
            )
        )
}
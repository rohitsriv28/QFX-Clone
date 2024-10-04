package com.example.qfxclone.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    onNext: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    textColor: Color = if (isSystemInDarkTheme()) Color.White else Color.Black,
    isError: Boolean = false,
) {

    val focusManager = LocalFocusManager.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        modifier = modifier.width(320.dp),
        shape = ShapeDefaults.Medium,
        label = label,
        isError = isError,
        leadingIcon = leadingIcon,
        placeholder = {
            if (placeholder != null)
                Text(
                    text = placeholder,
                    style = TextStyle(color = textColor)
                )
        },
        textStyle = TextStyle(color = textColor),
        keyboardOptions = KeyboardOptions(
            imeAction = if (onDone != null) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNext?.invoke()
                focusManager.moveFocus(FocusDirection.Next)
            },
            onDone = {
                onDone?.invoke()
                softwareKeyboardController?.hide() // Hide the keyboard
            }
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun Abc() {
    Column {
        CustomInputField(
            value = "",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Greet",
            isError = false
        )
    }
}
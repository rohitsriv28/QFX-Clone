/*
package com.example.qfxclone.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.qfxclone.ui.components.isValidEmail
import com.example.qfxclone.ui.components.isValidPassword

class SignInViewModel : ViewModel() {
    var signInUiState by mutableStateOf(SignInUiState())

    fun onSignInEvent(signInEvent: SignInEvent) {
        when (signInEvent) {
            is SignInEvent.EmailChangeEvent -> {
                signInUiState = signInUiState.copy(
                    email = signInEvent.email,
                    isInvalidEmail = signInUiState.email.isNotEmpty() && !isValidEmail(
                        signInUiState.email
                    )
                )
            }

            is SignInEvent.PasswordChangeEvent -> {
                signInUiState = signInUiState.copy(
                    password = signInEvent.password,
                    isInvalidPassword = signInUiState.password.isNotEmpty() && isValidPassword(
                        signInUiState.password
                    )
                )
            }

            is SignInEvent.OnSignInClickedEvent -> {
                signInEvent.onClick()
            }

            is SignInEvent.OnRegisterNowClickedEvent -> {
                signInEvent.onClick()
            }
        }
    }
}

sealed class SignInEvent {
    data class EmailChangeEvent(val email: String) : SignInEvent()
    data class PasswordChangeEvent(val password: String) : SignInEvent()
    data class OnSignInClickedEvent(val onClick: () -> Unit) : SignInEvent()
    data class OnRegisterNowClickedEvent(val onClick: () -> Unit) : SignInEvent()
}*/

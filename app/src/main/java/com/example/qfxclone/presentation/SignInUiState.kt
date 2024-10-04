package com.example.qfxclone.presentation

data class SignInUiState(
    var email: String = "",
    var password: String = "",

    var signInSuccess: String = "",
    var error: String = "",
    var processing: Boolean = false,

    var isInvalidEmail: Boolean = false,
    var isInvalidPassword: Boolean = false
)
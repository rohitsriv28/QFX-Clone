package com.example.qfxclone.presentation

import java.util.Date

data class SignupUiState(
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    var dateOfBirth: Date? = null,
    var email: String = "",
    var password: String = "",

    var error: String? = null,
    var signupSuccess: Boolean = false,
    var processing: Boolean = false,

    var isInvalidFirstName: Boolean = false,
    var isInvalidMiddleName: Boolean = false,
    var isInvalidLastName: Boolean = false,
    var isInvalidEmail: Boolean = false,
    var isInvalidPassword: Boolean = false,
)
package com.example.qfxclone.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qfxclone.data.entities.UserEntity
import com.example.qfxclone.data.repository.UserRepository
import com.example.qfxclone.ui.components.isStrongPassword
import com.example.qfxclone.ui.components.isValidEmail
import com.example.qfxclone.ui.components.isValidFirstName
import com.example.qfxclone.ui.components.isValidLastName
import com.example.qfxclone.ui.components.isValidMiddleName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    var signupUiState by mutableStateOf(SignupUiState())
    var signInUiState by mutableStateOf(SignInUiState())


    fun onSignupEvent(signupEvent: SignupEvent) {
        when (signupEvent) {
            is SignupEvent.FirstNameChangeEvent -> {
                signupUiState = signupUiState.copy(
                    firstName = signupEvent.firstName,
                    isInvalidFirstName = signupUiState.firstName.isNotEmpty() && !isValidFirstName(
                        signupUiState.firstName
                    )
                )
            }

            is SignupEvent.MiddleNameChangeEvent -> {
                signupUiState = signupUiState.copy(
                    middleName = signupEvent.middleName,
                    isInvalidMiddleName = signupUiState.middleName.isNotEmpty() && !isValidMiddleName(
                        signupUiState.middleName
                    )
                )
            }

            is SignupEvent.LastNameChangeEvent -> {
                signupUiState = signupUiState.copy(
                    lastName = signupEvent.lastName,
                    isInvalidLastName = signupUiState.lastName.isNotEmpty() && !isValidLastName(
                        signupUiState.lastName
                    )
                )
            }

            is SignupEvent.DateChangeEvent -> {
                signupUiState = signupUiState.copy(
                    dateOfBirth = signupEvent.date
                )
            }

            is SignupEvent.EmailChangeEvent -> {
                signupUiState = signupUiState.copy(
                    email = signupEvent.email,
                    isInvalidEmail = signupUiState.lastName.isNotEmpty() && !isValidEmail(
                        signupUiState.email
                    )
                )
            }

            is SignupEvent.PasswordChangeEvent -> {
                signupUiState = signupUiState.copy(
                    password = signupEvent.password,
                    isInvalidPassword = signupUiState.lastName.isNotEmpty() && !isStrongPassword(
                        signupUiState.password
                    )
                )
            }

            is SignupEvent.OnSignupClickedEvent -> {
                val userEntity = UserEntity(
                    firstName = signupUiState.firstName,
                    middleName = signupUiState.middleName,
                    lastName = signupUiState.lastName,
                    date = signupUiState.dateOfBirth,
                    email = signupUiState.email,
                    password = signupUiState.password
                )

                viewModelScope.launch {
                    try {
                        signupUiState = signupUiState.copy(
                            processing = true
                        )
                        delay(3000)

                        addUserToDb(userEntity)
                        signupEvent.onClick()

                    } catch (e: Exception) {
                        signupUiState = signupUiState.copy(
                            error = e.message
                        )
                    } finally {
                        signupUiState = signupUiState.copy(
                            processing = false
                        )
                    }
                }
            }

            is SignupEvent.RequestFocusOnField -> {
                when (signupEvent.fieldName) {
                    "FirstName" -> {

                    }

                    "MiddleName" -> {

                    }

                    "LastName" -> {

                    }
                }

            }
        }
    }

    private suspend fun addUserToDb(userEntity: UserEntity) = withContext(Dispatchers.IO) {
        userRepository.addUser(userEntity)
    }


    fun onSignInEvent(signInEvent: SignInEvent) {
        when (signInEvent) {
            is SignInEvent.EmailChangeEvent -> {
                signInUiState = signInUiState.copy(
                    email = signInEvent.email
                )
            }

            is SignInEvent.PasswordChangeEvent -> {
                signInUiState = signInUiState.copy(
                    password = signInEvent.password
                )
            }

            is SignInEvent.OnSignInClickedEvent -> {
                viewModelScope.launch {
                    val email = signInUiState.email
                    val password = signInUiState.password
                    var user: UserEntity? = null
                    withContext(Dispatchers.IO) {
                        val deferred =
                            async { userRepository.getUserByEmailAndPassword(email, password) }
                        user = deferred.await()
                    }
                    if (user != null) {
                        signInUiState = signInUiState.copy(
                            signInSuccess = "Successfully Logged In!"
                        )
                        signInEvent.onClick(user!!)
                    } else {
                        signInUiState = signInUiState.copy(
                            error = "Email or Password invalid!"
                        )
                        signInEvent.onError("Email or Password combination invalid!")
                    }
                }
            }

            is SignInEvent.OnRegisterNowClickedEvent -> {
            }
        }
    }
}


sealed class SignupEvent {
    data class FirstNameChangeEvent(val firstName: String) : SignupEvent()
    data class MiddleNameChangeEvent(val middleName: String) : SignupEvent()
    data class LastNameChangeEvent(val lastName: String) : SignupEvent()
    data class DateChangeEvent(val date: Date? = null) : SignupEvent()
    data class EmailChangeEvent(val email: String) : SignupEvent()
    data class PasswordChangeEvent(val password: String) : SignupEvent()
    data class OnSignupClickedEvent(val onClick: () -> Unit) : SignupEvent()
    data class RequestFocusOnField(val fieldName: String) : SignupEvent()
}

sealed class SignInEvent {
    data class EmailChangeEvent(val email: String) : SignInEvent()
    data class PasswordChangeEvent(val password: String) : SignInEvent()
    data class OnSignInClickedEvent(
        val onClick: (UserEntity) -> Unit,
        val onError: (String) -> Unit
    ) : SignInEvent()

    data class OnRegisterNowClickedEvent(val onClick: () -> Unit) : SignInEvent()
}
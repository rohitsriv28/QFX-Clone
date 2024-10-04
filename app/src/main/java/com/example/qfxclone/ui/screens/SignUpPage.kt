package com.example.qfxclone.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qfxclone.R
import com.example.qfxclone.presentation.AuthViewModel
import com.example.qfxclone.presentation.SignupEvent
import com.example.qfxclone.presentation.SignupUiState
import com.example.qfxclone.ui.components.CustomInputField
import com.example.qfxclone.ui.components.DatePickerTextField
import com.example.qfxclone.ui.components.MyDefaultValues


@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {

    val state: SignupUiState = viewModel.signupUiState

    val navigateToSignIn = {
        navController.popBackStack()
    }

    SignUpForm(
        state,
        viewModel::onSignupEvent,
        navigateToSignIn
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpForm(
    state: SignupUiState,
    onSignupEvent: (signupEvent: SignupEvent) -> Unit,
    backToLogin: () -> Boolean
) {
    var loading by remember {
        mutableStateOf(false)
    }

    val logo = painterResource(id = R.drawable.nfxlogo)
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "SignUp", color = Color.White) },
            navigationIcon = {
                Icon(
                    painter = logo,
                    contentDescription = "",
                    tint = Color(245, 234, 29, 255),
                    modifier = Modifier
                        .size(80.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(MyDefaultValues.defaultBgColor)
        )
    },
        bottomBar = {
            BottomAppBar(Modifier.height(50.dp), containerColor = MyDefaultValues.defaultBgColor) {

            }
        }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(ScrollState(1)),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "First Name", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.firstName,
                onValueChange = { onSignupEvent(SignupEvent.FirstNameChangeEvent(it)) },
                placeholder = "Enter your First Name!",
                modifier = Modifier.fillMaxWidth(),
                onNext = { KeyboardOptions(imeAction = ImeAction.Next) },
            )
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Middle Name(optional)", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.middleName,
                onValueChange = { onSignupEvent(SignupEvent.MiddleNameChangeEvent(it)) },
                placeholder = "Enter your Middle Name!",
                modifier = Modifier.fillMaxWidth(),
                onNext = { KeyboardOptions(imeAction = ImeAction.Next) },
            )
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Last Name", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.lastName,
                onValueChange = { onSignupEvent(SignupEvent.LastNameChangeEvent(it)) },
                placeholder = "Enter your Last Name!",
                modifier = Modifier.fillMaxWidth(),
                onNext = { KeyboardOptions(imeAction = ImeAction.Next) },
            )
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Date of Birth", fontWeight = FontWeight.SemiBold)
            DatePickerTextField(onSignupEvent)
            Text(text = "Email", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.email,
                onValueChange = { onSignupEvent(SignupEvent.EmailChangeEvent(it)) },
                placeholder = "Enter your email here!",
                modifier = Modifier.fillMaxWidth(),
                onNext = { KeyboardOptions(imeAction = ImeAction.Next) },
            )
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Password", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.password,
                onValueChange = { onSignupEvent(SignupEvent.PasswordChangeEvent(it)) },
                placeholder = "Enter your password!",
                modifier = Modifier.fillMaxWidth(),
                onDone = { KeyboardOptions(imeAction = ImeAction.Done) }
            )
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = MyDefaultValues.defaultSpace)
                OutlinedButton(
                    onClick = {
                        loading = true
                        softwareKeyboardController?.hide()
                        onSignupEvent(SignupEvent.OnSignupClickedEvent { backToLogin() })
                    },
                    colors = ButtonDefaults.buttonColors(MyDefaultValues.defaultButtonColor),
                    shape = ShapeDefaults.Small
                ) {
                    Text(text = "Sign Up")
                }
            }
            if (state.processing)
                SignUpProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpProgressIndicator() {

    BasicAlertDialog(
        onDismissRequest = { }, modifier = Modifier
            .size(50.dp)
            .fillMaxWidth()
    ) {
        Card {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                val rotationAngle = remember { Animatable(0f) }
                LaunchedEffect(Unit) {
                    rotationAngle.animateTo(
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 2000),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                }

                CircularProgressIndicator(
                    progress = { 0.3f },
                    modifier = Modifier.rotate(rotationAngle.value),
                    color = MyDefaultValues.defaultButtonColor,
                    strokeWidth = 3.dp,
                    trackColor = Color.Gray,
                )
            }
        }
    }
}


package com.example.qfxclone.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.qfxclone.R
import com.example.qfxclone.presentation.AuthViewModel
import com.example.qfxclone.presentation.SignInEvent
import com.example.qfxclone.presentation.SignInUiState
import com.example.qfxclone.ui.components.CustomInputField
import com.example.qfxclone.ui.components.MyDefaultValues
import com.example.qfxclone.ui.navigation.Routes

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val state: SignInUiState = viewModel.signInUiState
    val navigateToHome = {
        navController.popBackStack()
        navController.navigate(Routes.HOME.path)
    }
    val navigateToSignUp = {
        navController.navigate(Routes.SIGNUP.path)
    }
    SignInForm(
        state,
        viewModel::onSignInEvent,
        navigateToSignUp,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignInForm(
    state: SignInUiState,
    onSignInEvent: (signInEvent: SignInEvent) -> Unit,
    navigateToSignUp: () -> Unit,
    navController: NavHostController,

) {
    val logo = painterResource(id = R.drawable.nfxlogo)
    val snackBarHostState = remember { SnackbarHostState() }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.error) {
        state.error.takeIf { it.isNotBlank() }?.let { errorMessage ->
            snackBarHostState.showSnackbar(errorMessage)
        }
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "SignIn", color = Color.White) },
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
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { snackBarData ->
                Snackbar(snackbarData = snackBarData)
            }
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Email", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.email,
                onValueChange = { onSignInEvent(SignInEvent.EmailChangeEvent(it)) },
                placeholder = "Enter your email.",
                modifier = Modifier.fillMaxWidth(),
                onNext = { KeyboardOptions(imeAction = ImeAction.Next) },
            )

            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Password", fontWeight = FontWeight.SemiBold)
            CustomInputField(
                value = state.password,
                onValueChange = { onSignInEvent(SignInEvent.PasswordChangeEvent(it)) },
                placeholder = "Enter your password",
                modifier = Modifier.fillMaxWidth(),
                onDone = { KeyboardOptions(imeAction = ImeAction.Done) },
            )
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Text(text = "Forgot Password??", fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { })
            Spacer(modifier = MyDefaultValues.defaultSpace)
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    onClick = {
                        softwareKeyboardController?.hide()
                        onSignInEvent(SignInEvent.OnSignInClickedEvent(
                            onClick = {
                                navController.navigate(Routes.HOME.path) {
                                    popUpTo(Routes.LOGIN.path) {
                                        inclusive = true
                                    }
                                }
                            },
                            onError = { }
                        ))
                    },
                    colors = ButtonDefaults.buttonColors(MyDefaultValues.defaultButtonColor),
                    shape = ShapeDefaults.Small
                ) {
                    Text(text = "Sign In")
                }
                Spacer(modifier = MyDefaultValues.defaultSpace)
                Row {
                    Text(
                        text = "Not a member?\t",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Sign Up!",
                        modifier = Modifier.clickable { navigateToSignUp() },
                        fontSize = 16.sp,
                        color = Color(14, 121, 178, 255),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

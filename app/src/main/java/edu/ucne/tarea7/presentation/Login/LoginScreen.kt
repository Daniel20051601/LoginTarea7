package edu.ucne.tarea7.presentation.Login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import edu.ucne.tarea7.presentation.navigation.Screen
import edu.ucne.tarea7.ui.theme.Tarea7Theme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is LoginEffect.NavigateHome ->
                    navController.navigate(Screen.Home.createRoute(effect.usuarioId))
            }
        }
    }

    LoginBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoginBody(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.userMessage) {
        if (state.userMessage.isNotBlank()) {
            snackBarHostState.showSnackbar(state.userMessage)
            onEvent(LoginUiEvent.userMessageShown)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = if (state.isRegistering) "Sign in" else "Log in") }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                LinearWavyProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                val scroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .navigationBarsPadding()
                        .imePadding()
                        .verticalScroll(scroll),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = state.userName,
                        onValueChange = { onEvent(LoginUiEvent.userNameChanged(it)) },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = state.userNameError != null,
                        supportingText = {
                            state.userNameError?.let { Text(text = it) }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { onEvent(LoginUiEvent.passwordChanged(it)) },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = state.passwordError != null,
                        supportingText = {
                            state.passwordError?.let { Text(text = it) }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (state.isRegistering) {
                                onEvent(LoginUiEvent.submitRegistration)
                            } else {
                                onEvent(LoginUiEvent.submitLogin)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = if (state.isRegistering) "Registrar" else "Iniciar Sesion")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (state.isRegistering) "Ya tienes una cuenta?" else "No tienes una cuenta?",
                        modifier = Modifier
                            .clickable { onEvent(LoginUiEvent.registerModeClicked) }
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginBodyPreview(){
    Tarea7Theme {
        LoginBody(
            state = LoginUiState(
                userName = "test",
                password = "test",
                isRegistering = false,
                isLoading = false,
            ),
            onEvent = {}
        )
    }
}

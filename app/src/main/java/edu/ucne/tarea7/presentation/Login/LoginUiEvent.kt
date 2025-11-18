package edu.ucne.tarea7.presentation.Login

import edu.ucne.tarea7.presentation.Home.HomeUiEvent

interface LoginUiEvent {
    data class userNameChanged(val userName: String) : LoginUiEvent
    data class passwordChanged(val password: String) : LoginUiEvent
    data object registerModeClicked: LoginUiEvent
    data object submitRegistration: LoginUiEvent
    data object submitLogin: LoginUiEvent
    data object loginModeClicked: LoginUiEvent
    data object userMessageShown: LoginUiEvent
}
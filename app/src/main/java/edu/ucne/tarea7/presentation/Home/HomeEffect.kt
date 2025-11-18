package edu.ucne.tarea7.presentation.Home

sealed interface HomeEffect {
    data object NavigateLogin : HomeEffect
}
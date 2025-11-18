package edu.ucne.tarea7.presentation.Login


sealed interface LoginEffect {
    data class NavigateHome(val usuarioId: Int) : LoginEffect
}
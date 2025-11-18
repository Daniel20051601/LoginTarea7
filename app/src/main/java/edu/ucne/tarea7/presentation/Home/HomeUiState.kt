package edu.ucne.tarea7.presentation.Home

import edu.ucne.tarea7.dominio.model.Usuarios

data class HomeUiState(
    val usuarioId: Int? = null,
    val isLoadingUser: Boolean = false,
    val isLoadingList: Boolean = false,
    val listUsuarios: List<Usuarios> = emptyList(),
    val userName: String = "",
    val userNameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val userMessage: String? = null,
    val showDialog: Boolean = false
)

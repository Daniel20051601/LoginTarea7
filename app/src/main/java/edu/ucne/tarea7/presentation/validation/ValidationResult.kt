package edu.ucne.tarea7.presentation.validation

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null,
    val usuarioId: Int? = null
)

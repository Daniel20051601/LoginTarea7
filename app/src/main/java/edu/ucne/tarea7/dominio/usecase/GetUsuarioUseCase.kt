package edu.ucne.tarea7.dominio.usecase

import edu.ucne.tarea7.dominio.repository.UsuarioRepository
import javax.inject.Inject

class GetUsuarioUseCase @Inject constructor(
    private val repository: UsuarioRepository
){
    suspend operator fun invoke(id: Int) = repository.getUsuario(id)
}
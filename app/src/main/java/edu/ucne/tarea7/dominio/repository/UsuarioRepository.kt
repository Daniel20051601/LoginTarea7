package edu.ucne.tarea7.dominio.repository

import edu.ucne.tarea7.data.remote.Resource
import edu.ucne.tarea7.dominio.model.Usuarios
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    suspend fun getUsuarios(): Flow<List<Usuarios>>
    suspend fun getUsuario(id: Int): Resource<Usuarios?>
    suspend fun putUsuario(id: Int, usuario: Usuarios): Resource<Unit>
    suspend fun postUsuario(usuario: Usuarios): Resource<Usuarios?>
}
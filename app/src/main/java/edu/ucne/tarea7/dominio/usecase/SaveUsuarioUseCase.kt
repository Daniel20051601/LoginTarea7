package edu.ucne.tarea7.dominio.usecase

import edu.ucne.tarea7.data.remote.Resource
import edu.ucne.tarea7.dominio.model.Usuarios
import edu.ucne.tarea7.dominio.repository.UsuarioRepository
import javax.inject.Inject

class SaveUsuarioUseCase @Inject constructor(
    private val repository: UsuarioRepository
) {
    suspend operator fun invoke(id: Int, usuario: Usuarios): Resource<Usuarios?> {
        return if(id == 0){
            repository.postUsuario(usuario)
        }else{
            when(repository.putUsuario(id, usuario)){
                is Resource.Success -> Resource.Success(usuario)
                is Resource.Error -> Resource.Error("Error al actualizar el usuario")
                else -> Resource.Error("Error desconocido")
            }
        }
    }
}

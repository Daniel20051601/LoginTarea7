package edu.ucne.tarea7.data.mapper

import edu.ucne.tarea7.data.remote.dto.UsuariosDto
import edu.ucne.tarea7.dominio.model.Usuarios

fun Usuarios.toDto() : UsuariosDto = UsuariosDto(
    usuarioId =  usuarioId,
    userName = userName,
    password = password
)

fun UsuariosDto.toDomain(): Usuarios = Usuarios(
    usuarioId = usuarioId,
    userName = userName,
    password = password
)
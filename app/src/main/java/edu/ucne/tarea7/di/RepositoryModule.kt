package edu.ucne.tarea7.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.tarea7.data.repository.UsuarioRepositoryImpl
import edu.ucne.tarea7.dominio.repository.UsuarioRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGastoRepository(
        impl: UsuarioRepositoryImpl
    ): UsuarioRepository
}
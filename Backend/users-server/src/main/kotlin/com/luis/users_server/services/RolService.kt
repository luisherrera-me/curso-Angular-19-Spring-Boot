package com.luis.users_server.services

import com.luis.users_server.entities.Rol
import com.luis.users_server.repository.RolRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RolService @Autowired constructor(
    private val rolRepository: RolRepository
) {

    fun getAllRol(): List<Rol> = rolRepository.findAll()

    fun getRolById(id: Long): Rol = rolRepository.findById(id).orElse(null)

    fun createRol(rol: Rol): Rol = rolRepository.save(rol)

    fun updateRol(id: Long, rol: Rol): Rol?{
        return if (rolRepository.existsById(id)){
            rolRepository.save(rol.copy(id = id))
        } else{
            null
        }
    }

    fun deleteRol(id: Long, rol: Rol): Boolean {
        return if (rolRepository.existsById(id)){
            rolRepository.save(rol.copy(id = id))
            true
        } else{
            false
        }
    }


}
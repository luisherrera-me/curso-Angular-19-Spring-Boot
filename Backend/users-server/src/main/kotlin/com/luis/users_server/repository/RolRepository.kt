package com.luis.users_server.repository

import com.luis.users_server.entities.Rol
import org.springframework.data.jpa.repository.JpaRepository

interface RolRepository: JpaRepository<Rol, Long> {
}
package com.luis.users_server.entities

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
data class Rol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?= null,

    val name: String,
    val description: String,
    val logo: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

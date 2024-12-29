package com.luis.users_server.entities

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "users")
data class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ?= null,

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    val rol: Rol,

    val name: String,

    val photo: String,

    val address: String,

    val email: String,

    val password: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime = LocalDateTime.now()
)
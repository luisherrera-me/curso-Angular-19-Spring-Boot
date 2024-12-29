package com.luis.users_server.entities

import jakarta.persistence.*
import java.time.LocalDateTime


data class UsersRequest (
    val rolId: Long,
    val name: String,
    val photo: String,
    val address: String,
    val email: String,
    val password: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
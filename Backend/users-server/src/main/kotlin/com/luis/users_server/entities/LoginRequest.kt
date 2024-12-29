package com.luis.users_server.entities

data class LoginRequest(
    val email: String,
    val password: String
)

data class EmailRequest(
    val email: String
)


data class UserNameRequest(
    val name: String
)
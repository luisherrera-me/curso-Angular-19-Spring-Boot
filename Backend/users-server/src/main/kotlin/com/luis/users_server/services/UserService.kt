package com.luis.users_server.services

import com.luis.users_server.entities.LoginRequest
import com.luis.users_server.entities.Users
import com.luis.users_server.repository.RolRepository
import com.luis.users_server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val rolRepository: RolRepository,
    //private val jwtUtils: JwtUtil,
    //private val passwordEncoder: PasswordEncoder
) {

    fun authenticate(loginRequest: LoginRequest): String {
        val user = userRepository.findByEmail(loginRequest.email)
            ?: throw Exception("Usuario no encontrado")

        //if (!passwordEncoder.matches(loginRequest.password , user.password)){
            //throw Exception("Password incorecto")
        //}
        val roles = listOf(user.rol.name)
        return "token"
    }

    fun getAllUsers(): List<Users> = userRepository.findAll()

    fun searchUserByName(name: String): List<Users>{
        return userRepository.findByNameContainingIgnoreCase(name)
    }

    fun getUserById(id: Long): Users? = userRepository.findById(id).orElse(null)

    fun getUserByEmail(email: String): Users? = userRepository.findByEmail(email)

    fun createUser(user: Users): Users{
        val rol = rolRepository.findById(user.rol.id!!).orElseThrow{RuntimeException("Rol not Found")}
        return userRepository.save(user.copy(rol = rol))
    }

    fun updateUser (id: Long, user: Users): Users? {
        val rol = rolRepository.findById(user.rol.id!!).orElseThrow{RuntimeException("Rol not Found")}
        return if (userRepository.existsById(id)){
            return userRepository.save(user.copy(rol = rol))
        } else {
            null
        }
    }

    fun deleteUser(id: Long): Boolean {
        return if (userRepository.existsById(id)){
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }

}
package com.luis.users_server.controllers

import com.luis.users_server.entities.EmailRequest
import com.luis.users_server.entities.UserNameRequest
import com.luis.users_server.entities.Users
import com.luis.users_server.entities.UsersRequest
import com.luis.users_server.services.RolService
import com.luis.users_server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(
    private val userService: UserService,
    private val rolService: RolService
) {
    @GetMapping
    fun getAllUsers(): ResponseEntity<Any>{
        return try {
            val users = userService.getAllUsers()
            ResponseEntity.ok(mapOf(
                "status" to HttpStatus.OK.value(),
                "message" to "Usuarios Obtenidos exitosamente.",
                "data" to users
            ))
        } catch (e: Exception){
            handleMessage(e, "Error al obtener los usuarios")
        }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any>{
        return try {
            val user = userService.getUserById(id)
            if (user != null){
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.OK.value(),
                    "message" to "Usuarios encontrado.",
                    "data" to user
                ))
            }else{
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.NOT_FOUND.value(),
                    "message" to "Usuarios no encontrado con id: $id",
                    "errorCode" to "USER_NOT_FOUND"
                ))
            }
        }catch (e: Exception){
            handleMessage(e, "Error al obtener el usuario con el id: $id ")
        }
    }

    @PostMapping("/email")
    fun getUserById(@RequestBody emailRequest: EmailRequest): ResponseEntity<Any>{
        return try {
            val user = userService.getUserByEmail(emailRequest.email)
            if (user != null){
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.OK.value(),
                    "message" to "Usuarios encontrado.",
                    "data" to user
                ))
            }else{
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.NOT_FOUND.value(),
                    "message" to "Usuarios no encontrado con el email: ${emailRequest.email}",
                    "errorCode" to "USER_NOT_FOUND"
                ))
            }
        }catch (e: Exception){
            handleMessage(e, "Error al obtener el usuario con el id: ${emailRequest.email} ")
        }
    }

    @PostMapping("/search")
    fun getUserByName(@RequestBody userNameRequest: UserNameRequest): ResponseEntity<Any>{
        return try {
            val  user = userService.searchUserByName(userNameRequest.name)
            ResponseEntity.ok(mapOf(
                "status" to HttpStatus.OK.value(),
                "message" to "Usuarios encontrado.",
                "data" to user
            ))
        }catch (e: Exception){
            handleMessage(e, "Error al obtener el usuario")
        }
    }

    @PostMapping
    fun createUser(@RequestBody usersRequest: UsersRequest): ResponseEntity<Any>{
        return try {
            val rol = rolService.getRolById(usersRequest.rolId)
            if (rol == null){
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.NOT_FOUND.value(),
                    "message" to "Rol no encontrado con el id: ${usersRequest.rolId}",
                    "errorCode" to "ROL_NOT_FOUND"
                ))
            }

            val user = Users(
                rol = rol,
                name = usersRequest.name,
                photo = usersRequest.photo,
                address = usersRequest.address,
                email = usersRequest.email,
                password = usersRequest.password,
                createdAt = LocalDateTime.now()
            )
            val saveUser = userService.createUser(user)
            ResponseEntity.ok(mapOf(
                "status" to HttpStatus.OK.value(),
                "message" to "Usuarios encontrado.",
                "data" to saveUser
            ))

        }catch (e: Exception){
            handleMessage(e, "Error al crear el usuario")
        }
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long ,@RequestBody usersRequest: UsersRequest): ResponseEntity<Any>{
        return try {
            val rol = rolService.getRolById(usersRequest.rolId)
            if (rol == null){
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.NOT_FOUND.value(),
                    "message" to "Rol no encontrado con el id: ${usersRequest.rolId}",
                    "errorCode" to "ROL_NOT_FOUND"
                ))
            }

            val user = Users(
                rol = rol,
                name = usersRequest.name,
                photo = usersRequest.photo,
                address = usersRequest.address,
                email = usersRequest.email,
                password = usersRequest.password,
                createdAt = LocalDateTime.now()
            )
            val saveUser = userService.createUser(user)
            ResponseEntity.ok(mapOf(
                "status" to HttpStatus.OK.value(),
                "message" to "Usuarios encontrado.",
                "data" to saveUser
            ))
        }catch (e: Exception){
            handleMessage(e, "Error al crear el usuario")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any>{
        return try {
            val isDeleted = userService.deleteUser(id)
            if (isDeleted){
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.OK.value(),
                    "message" to "Usuarios eliminado exitosamente."
                ))
            } else{
                ResponseEntity.ok(mapOf(
                    "status" to HttpStatus.NOT_FOUND.value(),
                    "message" to "Usuario no encontrado con el id: $id",
                    "errorCode" to "USER_NOT_FOUND"
                ))
            }
        }catch (e: Exception){
            handleMessage(e, "Error al eliminar el usuario con el id: $id")
        }
    }


    private fun handleMessage(e: Exception, customMessage: String): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "message" to customMessage,
            "errorcode" to "INTERNAL_SERVER_ERROR",
            "detalils" to e.localizedMessage
        ))
    }
}
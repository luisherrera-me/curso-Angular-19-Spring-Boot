package com.luis.users_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UsersServerApplication

fun main(args: Array<String>) {
	runApplication<UsersServerApplication>(*args)
}

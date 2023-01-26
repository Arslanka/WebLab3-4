package com.example.arslanka.weblab4.services

import com.example.arslanka.weblab4.models.User
import com.example.arslanka.weblab4.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun login(user: User): User {
        val savedUser = findUserByLogin(user.login) ?: throw RuntimeException("User with this login does not exist ")
        if (!user.hashedPassword.contentEquals(savedUser.hashedPassword)) throw RuntimeException("Incorrect password")
        return savedUser
    }

    fun register(user: User): User {
        return saveNewUser(user)
    }

    fun findUserById(id: Int) = userRepository.findById(id)

    fun findUserByLogin(login: String) = userRepository.findByLogin(login)

    fun saveNewUser(user: User) = userRepository.insert(user)
}
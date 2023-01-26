package com.example.arslanka.weblab4.controllers

import com.example.arslanka.weblab4.models.User
import com.example.arslanka.weblab4.services.UserService
import io.tej.SwaggerCodgen.api.AuthApi
import io.tej.SwaggerCodgen.model.AuthRequest
import io.tej.SwaggerCodgen.model.AuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val userService: UserService) : AuthApi {

    override fun register(authRequest: AuthRequest, sessionUUID: String?): ResponseEntity<AuthResponse> {
        if (userService.findUserByLogin(login = authRequest.login) != null) throw RuntimeException("User with this login is already registered ")
        val savedUser = userService.register(
            User(
                login = authRequest.login,
                hashedPassword = authRequest.password.toByteArray()
            )
        )
        return ResponseEntity.ok(savedUser.toAuthResponse())
    }

    override fun login(authRequest: AuthRequest, sessionUUID: String?): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(
            userService.login(
                User(
                    login = authRequest.login,
                    hashedPassword = authRequest.password.toByteArray()
                )
            ).toAuthResponse()
        )
    }

    private fun User.toAuthResponse(): AuthResponse {
        return AuthResponse(
            id = this.id,
            login = this.login,
            hashedPassword = this.hashedPassword.toString()
        )
    }
}
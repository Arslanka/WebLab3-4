package com.example.arslanka.weblab4.exceptions

import com.example.arslanka.weblab4.uitls.Logging
import com.example.arslanka.weblab4.uitls.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    companion object : Logging {
        val log = logger()
    }

    private val codeToStatus = mapOf( //TODO Custom exceptions with error codes and messages
        "SMTH" to HttpStatus.BAD_REQUEST
    )

    @ExceptionHandler(value = [Exception::class])
    fun onAnyException(ex: Exception, request: HttpServletRequest): ResponseEntity<io.tej.SwaggerCodgen.model.Error> {
        val error = io.tej.SwaggerCodgen.model.Error(code = "123", message = ex.message ?: "")
        val response =
            ResponseEntity(
                error,
                codeToStatus[error.code] ?: HttpStatus.INTERNAL_SERVER_ERROR
            )
        log.error(ex.toString()) { "${request.method} ${request.requestURI}, HTTP.${response.statusCode} " }
        return response
    }
}
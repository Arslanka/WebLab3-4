package com.example.arslanka.weblab4.models

import org.jooq.sources.tables.records.UserAccountRecord

data class User(
    val id: Int? = null,
    val login: String,
    val hashedPassword: ByteArray,
)

fun UserAccountRecord.toUser(): User {
    return User(
        id = this.id,
        login = this.login,
        hashedPassword = this.passwordHash
    )
}

fun User.toRecord(): UserAccountRecord {
    return UserAccountRecord()
        .value2(this.login)
        .value3(this.hashedPassword)
}
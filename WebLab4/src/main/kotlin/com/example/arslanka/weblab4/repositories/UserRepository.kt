package com.example.arslanka.weblab4.repositories

import com.example.arslanka.weblab4.models.User
import com.example.arslanka.weblab4.models.toRecord
import com.example.arslanka.weblab4.models.toUser
import org.jooq.DSLContext
import org.jooq.sources.S335089
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val dslContext: DSLContext,
) {

    private val USER_ACCOUNT = S335089.S335089.USER_ACCOUNT

    fun findById(id: Int): User? {
        return dslContext.selectFrom(USER_ACCOUNT).where(USER_ACCOUNT.ID.eq(id)).fetchOne()?.toUser()
    }

    fun findByLogin(login: String): User? {
        return dslContext.selectFrom(USER_ACCOUNT).where(USER_ACCOUNT.LOGIN.eq(login)).fetchOne()?.toUser()
    }

    fun insert(user: User): User {
        val record = user.toRecord()
        return dslContext.insertInto(
            USER_ACCOUNT,
            USER_ACCOUNT.LOGIN,
            USER_ACCOUNT.PASSWORD_HASH
        ).values(
            record.login,
            record.passwordHash
        )
            .returning()
            .fetchSingle().toUser()
    }
}
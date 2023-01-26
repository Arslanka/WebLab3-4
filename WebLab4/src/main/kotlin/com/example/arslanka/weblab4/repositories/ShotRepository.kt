package com.example.arslanka.weblab4.repositories

import com.example.arslanka.weblab4.models.Shot
import com.example.arslanka.weblab4.models.toRecord
import com.example.arslanka.weblab4.models.toShot
import org.jooq.DSLContext
import org.jooq.sources.S335089
import org.springframework.stereotype.Repository

@Repository
class ShotRepository(
    private val dslContext: DSLContext,
) {

    private val SHOT_RESULT = S335089.S335089.SHOT_RESULT

    fun findByOwnerId(id: Int): Shot? {
        return dslContext.selectFrom(SHOT_RESULT).where(SHOT_RESULT.OWNER_ID.eq(id)).fetchOne()?.toShot()
    }

    fun findAllByOwnerId(id: Int): List<Shot> {
        return dslContext.selectFrom(SHOT_RESULT).where(SHOT_RESULT.OWNER_ID.eq(id)).fetch().map { it.toShot() }
    }

    fun deleteAllByOwnerId(id: Int): List<Shot> {
        return dslContext.deleteFrom(SHOT_RESULT).where(SHOT_RESULT.OWNER_ID.eq(id)).returning().fetch()
            .map { it.toShot() }
    }

    fun insert(tap: Shot): Shot {
        val record = tap.toRecord()
        return dslContext.insertInto(
            SHOT_RESULT,
            SHOT_RESULT.OWNER_ID,
            SHOT_RESULT.X,
            SHOT_RESULT.Y,
            SHOT_RESULT.R,
            SHOT_RESULT.REQUEST_TIME,
            SHOT_RESULT.STATUS
        ).values(
            record.ownerId,
            record.x,
            record.y,
            record.r,
            record.requestTime,
            record.status
        )
            .returning()
            .fetchSingle().toShot()
    }
}
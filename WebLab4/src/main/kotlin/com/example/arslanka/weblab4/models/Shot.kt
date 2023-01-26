package com.example.arslanka.weblab4.models

import org.jooq.sources.enums.ShotResultStatus
import org.jooq.sources.tables.records.ShotResultRecord
import java.math.BigDecimal
import java.time.OffsetDateTime

data class Shot(
    val ownerId: Int,
    val point2D: Point2D,
    val radius: BigDecimal,
    val requestTime: OffsetDateTime,
    var hitStatus: HitStatus? = null,
) {
    companion object {
        enum class HitStatus {
            HIT, MISS
        }
    }

    fun HitStatus?.toShotResultStatus(): ShotResultStatus? {
        return when (this) {
            HitStatus.HIT -> ShotResultStatus.HIT

            HitStatus.MISS -> ShotResultStatus.MISS
            else -> null
        }
    }

    fun HitStatus.toBoolean(): Boolean {
        return when (this) {
            HitStatus.HIT -> true

            HitStatus.MISS -> false
        }
    }
}

fun ShotResultRecord.toShot(): Shot {
    return Shot(
        ownerId = this.ownerId,
        point2D = Point2D(x = this.x.toBigDecimal(), y = this.y.toBigDecimal()),
        radius = this.r.toBigDecimal(),
        requestTime = this.requestTime,
        hitStatus = this.status.toHitStatus()
    )
}

fun ShotResultStatus.toHitStatus(): Shot.Companion.HitStatus? {
    return when (this.name.uppercase()) {
        "HIT" -> Shot.Companion.HitStatus.HIT

        "MISS" -> Shot.Companion.HitStatus.MISS
        else -> null
    }
}

fun Shot.toRecord(): ShotResultRecord {
    return ShotResultRecord()
        .value2(this.ownerId)
        .value3(this.point2D.x.toDouble())
        .value4(this.point2D.y.toDouble())
        .value5(this.radius.toDouble())
        .value6(this.requestTime)
        .value7(this.hitStatus.toShotResultStatus())
}

fun Boolean.toHitStatus(): Shot.Companion.HitStatus {
    return if (this) {
        Shot.Companion.HitStatus.HIT
    } else {
        Shot.Companion.HitStatus.MISS
    }
}

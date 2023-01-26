package com.example.arslanka.weblab4.services

import com.example.arslanka.weblab4.models.Shot
import com.example.arslanka.weblab4.models.toHitStatus
import org.springframework.stereotype.Service

@Service
class ShotPerformerService(
    private val shotService: ShotService,
    private val hitChecker: HitChecker,
) {
    fun performShot(shot: Shot): Shot {
        val hitStatus = (hitChecker.checkStatus
            (
            point2D = shot.point2D,
            radius = shot.radius
        )).toHitStatus()
        return shotService.insertShot(shot.apply { shot.hitStatus = hitStatus })
    }
}
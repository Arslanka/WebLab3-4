package com.example.arslanka.weblab4.controllers

import com.example.arslanka.weblab4.models.Point2D
import com.example.arslanka.weblab4.models.Shot
import com.example.arslanka.weblab4.services.ShotPerformerService
import com.example.arslanka.weblab4.services.ShotService
import io.tej.SwaggerCodgen.api.ShotApi
import io.tej.SwaggerCodgen.model.ShotRequest
import io.tej.SwaggerCodgen.model.ShotResponse
import io.tej.SwaggerCodgen.model.ShotTableResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ShotTableController(
    private val shotPerformerService: ShotPerformerService,
    private val shotService: ShotService,
) : ShotApi {
    override fun performShot(
        UID: Int,
        shotRequest: ShotRequest,
        sessionUUID: String?,
    ): ResponseEntity<ShotResponse> {
        val resultShot = shotPerformerService.performShot(
            shot = Shot(
                ownerId = UID,
                point2D = Point2D(x = shotRequest.x, y = shotRequest.y),
                radius = shotRequest.radius,
                requestTime = shotRequest.requestTime
            )
        )
        return ResponseEntity.ok(resultShot.toShotResponse())
    }

    override fun getShots(UID: Int, sessionUUID: String?): ResponseEntity<ShotTableResponse> {
        val listOfShots = shotService.finShotsByOwnerId(id = UID)
        return ResponseEntity.ok(ShotTableResponse(shots = listOfShots.flatMap { sequenceOf(it.toShotResponse()) }))
    }

    override fun deleteShots(UID: Int, sessionUUID: String?): ResponseEntity<ShotTableResponse> {
        val listOfDeletedShots = shotService.finShotsByOwnerId(id = UID)
        return ResponseEntity.ok(ShotTableResponse(shots = listOfDeletedShots.flatMap { sequenceOf(it.toShotResponse()) }))
    }

    private fun Shot.toShotResponse(): ShotResponse {
        return ShotResponse(
            x = this.point2D.x,
            y = this.point2D.y,
            radius = this.radius,
            requestTime = this.requestTime,
            hit = this.hitStatus?.toBoolean()!!
        )
    }
}
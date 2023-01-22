package com.example.arslanka.weblab4.controllers

import com.example.arslanka.weblab4.models.Point2D
import com.example.arslanka.weblab4.services.HitCheckService
import io.tej.SwaggerCodgen.api.ShotApi
import io.tej.SwaggerCodgen.model.Shot
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ShotTableController(
    private val hitCheckService: HitCheckService,
) : ShotApi {

    override fun performShot(UID: String, shot: Shot, sessionUUID: String?): ResponseEntity<Shot> {
        return ResponseEntity.ok(
            Shot(
                x = shot.x,
                y = shot.y,
                radius = shot.radius,
                timestamp = shot.timestamp,
                hit = hitCheckService.checkStatus(
                    Point2D(x = shot.x, y = shot.y), radius = shot.radius
                )
            )
        )
    }
}
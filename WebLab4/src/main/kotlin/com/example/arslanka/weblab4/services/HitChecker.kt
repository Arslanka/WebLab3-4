package com.example.arslanka.weblab4.services

import com.example.arslanka.weblab4.models.Point2D
import java.math.BigDecimal

interface HitChecker {
    fun checkStatus(point2D: Point2D, radius: BigDecimal): Boolean
}
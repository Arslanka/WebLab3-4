package com.example.arslanka.weblab4.models.views

import java.math.BigDecimal

interface Shape {
    fun isHit(radius: BigDecimal, x: BigDecimal, y: BigDecimal): Boolean
}
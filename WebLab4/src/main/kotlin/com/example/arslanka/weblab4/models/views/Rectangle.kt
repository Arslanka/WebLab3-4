package com.example.arslanka.weblab4.models.views

import java.math.BigDecimal

class Rectangle(xDistance: Distance, yDistance: Distance) : Shape {
    private var xMultiplier = BigDecimal.ZERO
    private var yMultiplier = BigDecimal.ZERO

    init {
        xMultiplier = if (xDistance === Distance.HALF_R) BigDecimal.valueOf(2) else BigDecimal.ONE
        yMultiplier = if (yDistance === Distance.HALF_R) BigDecimal.valueOf(2) else BigDecimal.ONE
    }

    override fun isHit(radius: BigDecimal, x: BigDecimal, y: BigDecimal): Boolean {
        return (x.abs() * xMultiplier) <= radius && (y.abs() * yMultiplier <= radius)
    }
}
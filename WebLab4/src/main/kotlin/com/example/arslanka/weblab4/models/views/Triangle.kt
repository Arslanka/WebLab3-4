package com.example.arslanka.weblab4.models.views

import java.math.BigDecimal

class Triangle(xDistance: Distance, yDistance: Distance) : Shape {
    private var k = BigDecimal.ZERO
    private var bMulti = BigDecimal.ZERO

    init {
        k = if (xDistance == yDistance) {
            BigDecimal.ONE
        } else if (xDistance === Distance.R) {
            BigDecimal.valueOf(0.5)
        } else {
            BigDecimal.valueOf(2)
        }
        bMulti = if (yDistance === Distance.R) BigDecimal.ONE else BigDecimal.valueOf(0.5)
    }

    override fun isHit(radius: BigDecimal, x: BigDecimal, y: BigDecimal): Boolean {
        val b = bMulti * radius
        return y.abs() <= (-k * x.abs() + b)
    }
}
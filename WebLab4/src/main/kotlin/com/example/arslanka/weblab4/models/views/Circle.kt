package com.example.arslanka.weblab4.models.views

import java.math.BigDecimal

class Circle(radius: Distance) : Shape {
    private var multiplier: BigDecimal

    init {
        multiplier = if (radius == Distance.HALF_R)
            BigDecimal.valueOf(0.5)
        else
            BigDecimal.ONE
    }

    override fun isHit(radius: BigDecimal, x: BigDecimal, y: BigDecimal): Boolean {
        return (x * x + y * y) <= ((radius * multiplier) * (radius * multiplier))
    }
}

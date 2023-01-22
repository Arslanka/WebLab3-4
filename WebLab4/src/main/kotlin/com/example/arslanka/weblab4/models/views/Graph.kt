package com.example.arslanka.weblab4.models.views

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class Graph(
    private val topLeft: Shape? = null, // TODO relocate to prop or env
    private val topRight: Shape? = Rectangle(Distance.R, Distance.HALF_R),
    private val botRight: Shape? = Triangle(Distance.R, Distance.R),
    private val botLeft: Shape? = Circle(Distance.HALF_R),
) {

    fun isHit(x: BigDecimal, y: BigDecimal, radius: BigDecimal): Boolean {
        if (topLeft != null && x <= BigDecimal.ZERO && y >= BigDecimal.ZERO)
            return topLeft.isHit(radius, x, y)
        else if (topRight != null && x >= BigDecimal.ZERO && y >= BigDecimal.ZERO)
            return topRight.isHit(radius, x, y)
        else if (botRight != null && x >= BigDecimal.ZERO && y <= BigDecimal.ZERO)
            return botRight.isHit(radius, x, y)
        else if (botLeft != null && x <= BigDecimal.ZERO && y <= BigDecimal.ZERO)
            return botLeft.isHit(radius, x, y)
        return false
    }
}

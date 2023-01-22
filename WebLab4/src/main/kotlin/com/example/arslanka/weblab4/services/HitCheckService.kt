package com.example.arslanka.weblab4.services

import com.example.arslanka.weblab4.models.Point2D
import com.example.arslanka.weblab4.models.views.Graph
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class HitCheckService(private val graph: Graph) : HitChecker {
    override fun checkStatus(point2D: Point2D, radius: BigDecimal): Boolean {
        return isHit(x = point2D.x, y = point2D.y, radius = radius)
    }

    private fun isHit(x: BigDecimal, y: BigDecimal, radius: BigDecimal): Boolean {
        return graph.isHit(x = x, y = y, radius = radius)
    }
}
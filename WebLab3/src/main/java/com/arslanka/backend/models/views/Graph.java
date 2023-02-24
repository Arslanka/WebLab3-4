package com.arslanka.backend.models.views;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Graph {
    private final Shape topLeft;
    private final Shape topRight;
    private final Shape botRight;
    private final Shape botLeft;

    public Boolean isHit(Double radius, Double y, Double x) {
        if (topLeft != null && x <= 0 && y >= 0) {
            return topLeft.isHit(radius, x, y);
        } else if (topRight != null && x >= 0 && y >= 0) {
            return topRight.isHit(radius, x, y);
        } else if (botRight != null && x >= 0 && y <= 0) {
            return botRight.isHit(radius, x, y);
        } else if (botLeft != null && x <= 0 && y <= 0) {
            return botLeft.isHit(radius, x, y);
        }
        return false;
    }
}

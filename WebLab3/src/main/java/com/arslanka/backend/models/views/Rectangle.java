package com.arslanka.backend.models.views;

public class Rectangle implements Shape {
    private final double xMultiplier;
    private final double yMultiplier;

    public Rectangle(Distance xRad, Distance yRad) {
        if (xRad == Distance.HALF_R) {
            xMultiplier = 2.0d;
        } else {
            xMultiplier = 1.0d;
        }
        if (yRad == Distance.HALF_R) {
            yMultiplier = 2.0d;
        } else {
            yMultiplier = 1.0d;
        }
    }

    @Override
    public Boolean isHit( Double radius, Double x, Double y) {
        x = Math.abs(x);
        y = Math.abs(y);
        return (x * xMultiplier) <= radius && (y * yMultiplier) <= radius;
    }
}

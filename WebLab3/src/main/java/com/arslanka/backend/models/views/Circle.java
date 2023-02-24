package com.arslanka.backend.models.views;

public class Circle implements Shape {
    private final double multiplier;

    public Circle(Distance radius) {
        if (radius == Distance.HALF_R) {
            multiplier = 1.0d / 2.0d;
        } else {
            multiplier = 1.0d;
        }
    }

    @Override
    public Boolean isHit(Double radius, Double x, Double y) {
        return (x * x + y * y) <= ((radius * multiplier) * (radius * multiplier));
    }
}

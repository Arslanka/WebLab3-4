package com.arslanka.backend.models.views;

public class Triangle implements Shape {
    private final Double k;
    private final Double bMulti;

    public Triangle(Distance xRad, Distance yRad) {
        if (xRad.equals(yRad)) {
            k = 1.0d;
        } else if (xRad == Distance.R) {
            k = 1.0d / 2.0d;
        } else {
            k = 2.0d;
        }

        if (yRad == Distance.R) {
            bMulti = 1.0d;
        } else {
            bMulti = 1.0d / 2.0d;
        }
    }

    @Override
    public Boolean isHit(Double radius, Double x, Double y) {
        double b = bMulti * radius;
        y = Math.abs(y);
        x = Math.abs(x);
        return y <= (-k * x + b);
    }
}

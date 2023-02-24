package com.arslanka.backend.models.daos;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;

@Named(value = "point2D")
@RequestScoped
@Data
@NoArgsConstructor
public class Point2D {
    private Double x;

    private Double y;

    public Point2D(Double xCoor, Double yCoor) {
        this.x = xCoor;
        this.y = yCoor;
    }
}

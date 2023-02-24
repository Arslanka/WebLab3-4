package com.arslanka.backend.services;

import com.arslanka.backend.models.daos.Request;
import com.arslanka.backend.models.views.Circle;
import com.arslanka.backend.models.views.Distance;
import com.arslanka.backend.models.views.Graph;
import com.arslanka.backend.models.views.Rectangle;
import com.arslanka.backend.models.views.Triangle;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("hitCheckService")
@ApplicationScoped
public class HitCheckService {

    private final Graph graph;


    {
        graph = new Graph(
                null,
                new Rectangle(Distance.R, Distance.HALF_R),
                new Triangle(Distance.R, Distance.R),
                new Circle(Distance.HALF_R));
    }

    public Boolean checkStatus(Request request) {
        return isHit(request);
    }

    private Boolean isHit(Request request) {
        return graph.isHit(request.getRadius(), request.getPoint2D().getX(), request.getPoint2D().getY());
    }
}

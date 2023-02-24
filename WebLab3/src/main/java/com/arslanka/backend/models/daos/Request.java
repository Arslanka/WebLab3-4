package com.arslanka.backend.models.daos;

import java.time.OffsetDateTime;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.sources.tables.records.RequestRecord;

@Named("requestBean")
@RequestScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Inject
    @ManagedProperty("${userBean.id}")
    private Long id;
    @Inject
    private Point2D point2D;

    private Double radius = 1.;
    private OffsetDateTime requestTime;
    private Boolean hit;

    public RequestRecord toRecord() {
        return new RequestRecord(
                id,
                point2D.getX(),
                point2D.getY(),
                radius,
                requestTime,
                hit
        );
    }

    public TableRow toTableRow() {
        return new TableRow(
                id,
                point2D,
                radius,
                requestTime,
                hit
        );
    }
}

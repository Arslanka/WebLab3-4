package com.arslanka.backend.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.arslanka.backend.models.daos.Request;
import com.arslanka.backend.models.daos.TableRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tableCreationService")
@ApplicationScoped
public class TableCreationService {
    @Inject
    private RequestService requestService;

    @Inject
    private TableRow tableRow;

    public List<TableRow> getRequestTable(String sessionId, Double radius) throws SQLException {
        List<Request> requestList = requestService.findRequestsBySessionId(sessionId).stream()
                .filter(row -> Objects.equals(row.getRadius(),
                        radius)).toList();
        if (!requestList.isEmpty()) {
            setTableRow(requestList.get(requestList.size() - 1));
            return requestList.stream().map(Request::toTableRow).toList();
        } else {
            return Collections.emptyList();
        }
    }


    private void setTableRow(Request request) {
        tableRow.setId(request.getId());
        tableRow.setPoint2D(request.getPoint2D());
        tableRow.setRadius(request.getRadius());
        tableRow.setHit(request.getHit());
        tableRow.setRequestTime(request.getRequestTime());
    }
}

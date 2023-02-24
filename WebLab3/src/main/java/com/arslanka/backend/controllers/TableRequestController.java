package com.arslanka.backend.controllers;

import java.util.Collections;
import java.util.List;

import com.arslanka.backend.models.daos.Request;
import com.arslanka.backend.models.daos.TableRow;
import com.arslanka.backend.models.daos.Tap;
import com.arslanka.backend.services.RequestService;
import com.arslanka.backend.services.TableCreationService;
import com.arslanka.backend.services.TapService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("tableRequestController")
@ApplicationScoped
public class TableRequestController {

    @Inject
    private TableCreationService tableCreationService;

    @Inject
    private RequestService requestService;

    @Inject
    private TapService tapService;

    @Inject
    private Tap tap;

    @Inject
    private Request request;


    public List<TableRow> getTable() {
        try {
            return tableCreationService.getRequestTable(tap.getSessionId(), request.getRadius());
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void clearTable() {
        try {
            requestService.deleteBySessionId(tap.getSessionId());
            tapService.deleteBySessionId(tap.getSessionId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

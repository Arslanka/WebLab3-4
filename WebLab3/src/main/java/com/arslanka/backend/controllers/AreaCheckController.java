package com.arslanka.backend.controllers;

import java.time.OffsetDateTime;

import com.arslanka.backend.models.daos.Request;
import com.arslanka.backend.models.daos.Tap;
import com.arslanka.backend.services.HitCheckService;
import com.arslanka.backend.services.RequestService;
import com.arslanka.backend.services.TapService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("areaCheckController")
@ApplicationScoped
public class AreaCheckController {

    @Inject
    private HitCheckService hitCheckService;

    @Inject
    private RequestService requestService;

    @Inject
    private TapService tapService;

    @Inject
    private Tap tap;

    @Inject
    private Request request;

    public void checkHitAndSave() {
        try {
            request.setHit(hitCheckService.checkStatus(request));
            request.setRequestTime(OffsetDateTime.now());
            tapService.saveTap(tap);
            requestService.saveRequest(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.arslanka.backend.models.daos;

import java.util.random.RandomGenerator;

import com.arslanka.backend.services.SessionIdGetter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.sources.tables.records.TapRecord;

@Named("userBean")
@RequestScoped
@Data
@NoArgsConstructor
public class Tap {

    private Long id;

    @Inject
    private SessionIdGetter sessionIdGetter;
    private String sessionId;


    @PostConstruct
    public void init() {
        this.id = RandomGenerator.getDefault().nextLong();
        this.sessionId = sessionIdGetter.getCurrentSessionId();

    }

    public Tap(Long id, String sessionId) {
        this.id = id;
        this.sessionId = sessionId;
    }

    public TapRecord toRecord() {
        return new TapRecord(
                id,
                sessionId
        );
    }
}

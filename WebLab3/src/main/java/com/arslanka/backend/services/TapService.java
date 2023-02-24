package com.arslanka.backend.services;

import java.sql.SQLException;
import java.util.List;

import com.arslanka.backend.db.repositories.TapRepository;
import com.arslanka.backend.models.daos.Tap;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("tapService")
@ApplicationScoped
public class TapService {
    private static final Logger logger = LoggerFactory.getLogger(TapService.class);
    @Inject
    private TapRepository tapRepository;


    public void saveTap(Tap tap) throws SQLException {
        tapRepository.upsert(tap);
        logger.info(String.format("%s %d %s", "Tap with id:", tap.getId(), "was saved"));
    }

    public void deleteBySessionId(String sessionId) throws SQLException {
        tapRepository.deleteBySessionId(sessionId);
    }

    public List<Tap> findAllTaps() throws SQLException {
        List<Tap> taps = tapRepository.findAllTaps().stream().toList();
        logger.info(String.format("%d %s", taps.size(), "types were found"));
        return taps;
    }

    public List<Long> findCurrentRequestIds(String sessionId) throws SQLException {
        List<Long> ids = tapRepository.findTapsBySessionId(sessionId).stream().map(Tap::getId).toList();
        logger.info(String.format("%d %s", ids.size(), "ids with current sessionId were found"));
        return ids;
    }

}

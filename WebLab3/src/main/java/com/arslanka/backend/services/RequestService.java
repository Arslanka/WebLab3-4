package com.arslanka.backend.services;

import java.sql.SQLException;
import java.util.List;

import com.arslanka.backend.db.repositories.RequestRepository;
import com.arslanka.backend.models.daos.Request;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("requestService")
@ApplicationScoped
public class RequestService {
    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    @Inject
    private TapService tapService;

    @Inject
    private RequestRepository requestRepository;

    public List<Request> findRequestsBySessionId(String sessionId) throws SQLException {
        List<Long> ids = tapService.findCurrentRequestIds(sessionId);
        logger.info(String.format("%d %s", ids.size(), "requests were found by current sessionId"));
        return requestRepository.findAllByIds(ids).stream().toList();
    }

    public Request findRequestById(Long id) throws SQLException {
        return requestRepository.findById(id);
    }

    public void saveRequest(Request request) throws SQLException {
        requestRepository.upsert(request);
        logger.info(String.format("%s %d %s", "Request with id:", request.getId(), "was saved"));
    }

    public void deleteBySessionId(String sessionId) throws SQLException {
        List<Long> ids = tapService.findCurrentRequestIds(sessionId);
        requestRepository.deleteAllByIds(ids);
    }

}

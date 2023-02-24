package com.arslanka.backend.db.repositories;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.arslanka.backend.models.daos.Point2D;
import com.arslanka.backend.models.daos.Request;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.jooq.exception.DataAccessException;
import org.jooq.sources.tables.records.RequestRecord;

import static org.jooq.sources.Tables.REQUEST;

@Named("requestRepository")
@ApplicationScoped
public class RequestRepository extends SimpleRepository {


    public Collection<Request> findAllByIds(List<Long> ids) throws SQLException {
        return withConnection(db -> db.selectFrom(REQUEST).where(REQUEST.ID.in(ids)).fetch(this::toRequest),
                DB_TYPE_POSTGRESQL);
    }

    public Request findById(Long id) throws SQLException {
        return withConnection(db -> db.selectFrom(REQUEST).where(REQUEST.ID.eq(id)).fetchOne(this::toRequest),
                DB_TYPE_POSTGRESQL);
    }

    public void upsert(Request request) throws SQLException {
        RequestRecord record = request.toRecord();
        withConnection(db -> db.insertInto(REQUEST, REQUEST.fields())
                .valuesOfRecords(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning(REQUEST.ID)
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("Can't insert request into Request table"))
                .get(REQUEST.ID), DB_TYPE_POSTGRESQL);
    }

    public void clear() throws SQLException {
        withConnection(db -> db.dropTable(REQUEST), DB_TYPE_POSTGRESQL);
    }

    public void deleteAllByIds(List<Long> ids) throws SQLException {
        withConnection(db -> db.deleteFrom(REQUEST).where(REQUEST.ID.in(ids)).execute(), DB_TYPE_POSTGRESQL);
    }

    private Request toRequest(RequestRecord record) {
        return new Request(record.getId(),
                new Point2D(record.getX(), record.getY()),
                record.getR(),
                record.getRequestTime(), record.getStatus());
    }


}

package com.arslanka.backend.db.repositories;


import java.sql.SQLException;
import java.util.Collection;

import com.arslanka.backend.models.daos.Tap;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.jooq.exception.DataAccessException;
import org.jooq.sources.tables.records.TapRecord;

import static org.jooq.sources.Tables.TAP;

@Named("tapRepository")
@ApplicationScoped
public class TapRepository extends SimpleRepository {


    public Collection<Tap> findTapsBySessionId(String sessionId) throws SQLException {
        return withConnection(db -> db.selectFrom(TAP).where(TAP.SESSION_ID.eq(sessionId)).fetch(this::toTapRecord),
                DB_TYPE_POSTGRESQL);
    }

    public Collection<Tap> findAllTaps() throws SQLException {
        return withConnection(db -> db.selectFrom(TAP).fetch(this::toTapRecord),
                DB_TYPE_POSTGRESQL);
    }

    public void upsert(Tap tap) throws SQLException {
        TapRecord record = tap.toRecord();
        withConnection(db ->
                        db.insertInto(TAP, TAP.fields())
                                .valuesOfRecords(record)
                                .onDuplicateKeyUpdate()
                                .set(record)
                                .returning(TAP.ID)
                                .fetchOptional()
                                .orElseThrow(() -> new DataAccessException("Can't insert tap into User table"))
                                .get(TAP.ID)
                , DB_TYPE_POSTGRESQL);
    }

    public void clear() throws SQLException {
        withConnection(db -> db.dropTable(TAP), DB_TYPE_POSTGRESQL);
    }

    public void deleteBySessionId(String sessionId) throws SQLException {
        withConnection(db -> db.deleteFrom(TAP).where(TAP.SESSION_ID.eq(sessionId)), DB_TYPE_POSTGRESQL);
    }

    private Tap toTapRecord(TapRecord record) {
        return new Tap(record.getId(), record.getSessionId());
    }
}


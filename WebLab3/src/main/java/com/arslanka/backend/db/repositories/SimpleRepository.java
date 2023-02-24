package com.arslanka.backend.db.repositories;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.arslanka.backend.db.Config;
import com.arslanka.backend.db.DatabaseStorage;
import com.arslanka.backend.db.HikariDatasourceFactory;
import com.arslanka.backend.db.HikariPoolCfg;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

@Named("SimpleRepository")
@ApplicationScoped
@NoArgsConstructor
public class SimpleRepository extends DatabaseStorage {


    private Config configDb = Config.load();

    private HikariPoolCfg hikariConfig = HikariPoolCfg.load();
    private DataSource dataSource;

    {
        dataSource = dataSource();
    }


    public SimpleRepository(final Config configDb, final HikariPoolCfg hikariConfig) {
        this.configDb = configDb;
        this.hikariConfig = hikariConfig;
        this.dataSource = dataSource();

    }

    @Override
    public <T> T withConnection(Action<T> action, SQLDialect sqlDialect) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            return action.execute(DSL.using(connection, sqlDialect));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public DataSource dataSource() {
        return HikariDatasourceFactory.dataSource(configDb, hikariConfig);
    }

    @Override
    public void disconnect() throws SQLException {
        if (this.dataSource != null) {
            this.dataSource = null;
        } else {
            throw new SQLException();
        }
    }

}

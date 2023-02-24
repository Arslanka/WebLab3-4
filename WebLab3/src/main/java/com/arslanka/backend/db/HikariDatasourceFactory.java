package com.arslanka.backend.db;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class HikariDatasourceFactory {

    public static DataSource dataSource(Config configDb, HikariPoolCfg config) {
        if (configDb == null || config == null) {
            return null;
        }
        final com.zaxxer.hikari.HikariConfig hikariConfig = new com.zaxxer.hikari.HikariConfig();
        hikariConfig.setJdbcUrl(configDb.database().createUrl());
        hikariConfig.setUsername(configDb.database().user());
        hikariConfig.setPassword(configDb.database().password());
        config.hikariConfig.properties().forEach(hikariConfig::addDataSourceProperty);
        return new HikariDataSource(hikariConfig);
    }
}

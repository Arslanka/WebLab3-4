package com.arslanka.backend.db;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;


public record Config(@JsonProperty("database") Config.Database database) {

    private static final Path CONFIG_FILEPATH = Path.of("config.json").toAbsolutePath();

    public record Database(@JsonProperty(value = "type", required = true) String type,
                           @JsonProperty(value = "name") String name,
                           @JsonProperty(value = "home") String home,
                           @JsonProperty(value = "host", required = true) String host,
                           @JsonProperty(value = "port", required = true) int port,
                           @JsonProperty(value = "user", required = true) String user,
                           @JsonProperty(value = "password", required = true) String password) {
        @JsonCreator
        public Database(
                @JsonProperty("type") final String type,
                @JsonProperty("name") final String name,
                @JsonProperty("home") final String home,
                @JsonProperty("host") final String host,
                @JsonProperty("port") final int port,
                @JsonProperty("user") final String user,
                @JsonProperty("password") final String password
        ) {
            this.type = type;
            this.name = name;
            this.home = home;
            this.host = host;
            this.port = port;
            this.user = user;
            this.password = password;
        }

        public String createUrl() {
            return String.format(
                    "jdbc:%s://%s:%d/%s",
                    name, host, port, type
            );
        }
    }

    @JsonCreator
    public Config(Config.Database database) {
        this.database = database;
    }

    @SneakyThrows
    public static Config load(final Path filePath) {
        return (new ObjectMapper()).readValue(filePath.toFile(), Config.class);
    }

    public static Config load() {
        return load(CONFIG_FILEPATH);
    }
}

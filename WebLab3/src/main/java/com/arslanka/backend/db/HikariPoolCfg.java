package com.arslanka.backend.db;

import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public final class HikariPoolCfg {
    @JsonProperty(value = "hikari_config", required = true)
    public HikariPoolCfg.Config hikariConfig;

    private static final Path HIKARI_CONFIG_FILEPATH = Path.of("hikari_config.json").toAbsolutePath();

    public record Config(@JsonProperty(value = "hikari_properties") Map<String, String> properties) {
        @JsonCreator
        public Config(@JsonProperty("hikari_properties") Map<String, String> properties) {
            this.properties = properties;
        }
    }


    @JsonCreator
    public HikariPoolCfg(@JsonProperty("hikari_config") Config hikariConfig) {
        this.hikariConfig = hikariConfig;
    }

    @SneakyThrows
    public static HikariPoolCfg load(final Path filePath) {
        return (new ObjectMapper()).readValue(filePath.toFile(), HikariPoolCfg.class);
    }

    public static HikariPoolCfg load() {
        return load(HIKARI_CONFIG_FILEPATH);
    }
}

package com.vsign.backend.migration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FlywayMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void appliesSchemaAndSeedMigrations() {
        Integer usersTableCount = jdbcTemplate.queryForObject(
                "select count(*) from information_schema.tables where lower(table_schema) = 'public' and lower(table_name) = 'users'",
                Integer.class
        );

        Integer rolesCount = jdbcTemplate.queryForObject(
                "select count(*) from reference_roles",
                Integer.class
        );

        Integer appliedVersions = jdbcTemplate.queryForObject(
                "select count(distinct version) from flyway_schema_history where success = true and version is not null",
                Integer.class
        );

        assertThat(usersTableCount).isEqualTo(1);
        assertThat(rolesCount).isEqualTo(3);
        assertThat(appliedVersions).isEqualTo(2);
    }
}

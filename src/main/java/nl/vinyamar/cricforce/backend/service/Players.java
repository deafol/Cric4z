package nl.vinyamar.cricforce.backend.service;

import nl.vinyamar.cricforce.backend.CricForceConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

public class Players {

    public List<String> getPlayerNames() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(CricForceConfiguration.getDataSource());
        List<String> players = jdbcTemplate.queryForList("select initials || ' ' || prefix || ' ' || lastname as playerName from Player", String.class);
        return players;
    }
}

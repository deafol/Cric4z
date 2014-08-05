package nl.vinyamar.cricforce.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class Players {

    @Autowired
    private DataSource dataSource;

    public List<String> getPlayerNames() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> players = jdbcTemplate.queryForList("select initials || ' ' || prefix || ' ' || lastname as playerName from Player", String.class);
        return players;
    }
}

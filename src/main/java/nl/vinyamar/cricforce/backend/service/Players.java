package nl.vinyamar.cricforce.backend.service;

import nl.vinyamar.cricforce.backend.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Players {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public List<String> playerNames() {
        List<String> players = getJdbcTemplate().queryForList("select initials || ' ' || prefix || ' ' || lastname as playerName from Player", String.class);
        return players;
    }

    public List<Player> all() {
        PlayerMapper rowMapper = new PlayerMapper();
        getJdbcTemplate().query("select * from Player", rowMapper);
        return rowMapper.players;
    }

    public Player byId(String id) {
        PlayerMapper rowMapper = new PlayerMapper();
        getJdbcTemplate().query("select * from Player where id = " + id, rowMapper);
        return rowMapper.players.get(0);
    }

    //TODO refactor, very ugly
    class PlayerMapper implements RowMapper<Player> {

        final List<Player> players = new ArrayList<Player>();

        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getLong(1));
            player.setFirstName(rs.getString(2));
            player.setLastName(rs.getString(3));
            player.setPrefix(rs.getString(4));
            player.setInitials(rs.getString(5));
            players.add(player);
            return player;
        }
    }

}

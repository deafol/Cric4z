package nl.vinyamar.cricforce.backend.springconfig;

import javax.sql.DataSource;

public interface DBConfiguration {

    DataSource dataSource();
}

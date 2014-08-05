package nl.vinyamar.cricforce.backend;

import io.dropwizard.Configuration;
import nl.vinyamar.cricforce.backend.config.Test;

import javax.sql.DataSource;

public class CricForceConfiguration extends Configuration {

    private static DataSource applicationDatasource;

    public static DataSource getDataSource() {
        if (applicationDatasource == null) {
            applicationDatasource = Test.DataSource();
        }
        return applicationDatasource;
    }


}

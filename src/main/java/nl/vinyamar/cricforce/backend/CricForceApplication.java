package nl.vinyamar.cricforce.backend;

import com.github.joschi.dropwizard.java8.Java8Bundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.vinyamar.cricforce.backend.resources.PlayerResource;

public class CricForceApplication extends Application<CricForceConfiguration> {

    public static void main(String[] args) throws Exception {
        new CricForceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CricForceConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
    }

    @Override
    public void run(CricForceConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new PlayerResource());
    }
}

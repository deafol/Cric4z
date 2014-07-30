package nl.vinyamar.cricforce.backend;

import com.github.joschi.dropwizard.java8.Java8Bundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CricForceApplication extends Application<CricForceConfiguration> {
    @Override
    public void initialize(Bootstrap<CricForceConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
    }

    @Override
    public void run(CricForceConfiguration configuration, Environment environment) throws Exception {
    }
}

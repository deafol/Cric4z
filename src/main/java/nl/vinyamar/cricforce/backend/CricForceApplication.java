package nl.vinyamar.cricforce.backend;

import com.github.joschi.dropwizard.java8.Java8Bundle;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.server.linking.LinkFilter;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.vinyamar.cricforce.backend.springconfig.MainConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.Path;
import java.util.Map;

public class CricForceApplication extends Application<CricForceConfiguration> {

    private AnnotationConfigWebApplicationContext parentCtx;

    private AnnotationConfigWebApplicationContext context;

    public static void main(String[] args) throws Exception {
        new CricForceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CricForceConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
    }

    @Override
    public void run(CricForceConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().property(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LinkFilter.class);
        setupSpringContext(configuration);
        registerResources(environment);
    }

    private void setupSpringContext(CricForceConfiguration configuration) {
        parentCtx = new AnnotationConfigWebApplicationContext();
        parentCtx.refresh();
        parentCtx.getBeanFactory().registerSingleton("configuration", configuration);
        parentCtx.start();

        context = new AnnotationConfigWebApplicationContext();
        context.setParent(parentCtx);
        context.getEnvironment().setActiveProfiles(configuration.getProfiles());
        context.register(MainConfiguration.class);
        context.refresh();
        context.registerShutdownHook();
        context.start();
    }

    private void registerResources(Environment environment) {
        Map<String, Object> resourceBeans = context.getBeansWithAnnotation(Path.class);
        for (Map.Entry<String, Object> bean : resourceBeans.entrySet()) {
            environment.jersey().register(bean.getValue());
        }
    }
}

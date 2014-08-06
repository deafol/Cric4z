package nl.vinyamar.cricforce.backend;

import com.github.joschi.dropwizard.java8.Java8Bundle;
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
//        DefaultServerFactory serverFactory = (DefaultServerFactory) configuration.getServerFactory();
//        List<ConnectorFactory> applicationConnectors = serverFactory.getApplicationConnectors();
//        HttpConnectorFactory connectorFactory = (HttpConnectorFactory) applicationConnectors.get(0);
//        System.out.println(connectorFactory.getPort());
//        connectorFactory.setPort(9002);
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
        for(Map.Entry<String,Object> bean : resourceBeans.entrySet()) {
            environment.jersey().register(bean.getValue());
        }
    }
}

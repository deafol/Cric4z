package nl.vinyamar.cricforce.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class CricForceConfiguration extends Configuration {

    @JsonProperty
    private String profiles;

    public String getProfiles() {
        return profiles;
    }

}

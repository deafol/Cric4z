package nl.vinyamar.cricforce.backend;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CricForceConfiguration extends Configuration implements AssetsBundleConfiguration {

    @JsonProperty
    private String profiles;

    @JsonProperty
    @NotNull
    @Valid
    private AssetsConfiguration webapp;

    public String getProfiles() {
        return profiles;
    }

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return webapp;
    }
}

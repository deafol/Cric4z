package nl.vinyamar.cricforce.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.server.linking.Ref;

import java.net.URI;

public class Player {

    @Ref("player/{id}")
    @JsonProperty
    private URI player;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public long id;

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public String prefix;

    @JsonProperty
    public String initials;

}

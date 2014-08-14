package nl.vinyamar.cricforce.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.server.linking.Ref;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter @Setter @EqualsAndHashCode
public class Player {

    @Ref("player/{id}")
    @JsonProperty
    private URI player;

    @JsonProperty
    private long id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String prefix;

    @JsonProperty
    private String initials;

}

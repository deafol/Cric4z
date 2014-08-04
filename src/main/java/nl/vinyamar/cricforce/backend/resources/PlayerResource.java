package nl.vinyamar.cricforce.backend.resources;

import nl.vinyamar.cricforce.backend.service.Players;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @GET
    @Path("/players")
    public List<String> getAllPlayers(){
        return new Players().getPlayerNames();
    }
}

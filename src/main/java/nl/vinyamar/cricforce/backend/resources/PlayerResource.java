package nl.vinyamar.cricforce.backend.resources;

import nl.vinyamar.cricforce.backend.service.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class PlayerResource {

    @Autowired
    private Players players;

    @GET
    @Path("/players")
    public List<String> getAllPlayers(){
        return players.getPlayerNames();
    }
}

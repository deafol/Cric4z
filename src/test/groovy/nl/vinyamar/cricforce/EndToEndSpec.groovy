package nl.vinyamar.cricforce

import com.sun.jersey.api.client.Client
import nl.vinyamar.cricforce.backend.CricForceApplication
import spock.lang.Specification

import javax.ws.rs.core.MediaType

class EndToEndSpec extends Specification {

    def "return a list with all playernames in JSON"() {
      setup:
        CricForceApplication.main("server")
        def client = new Client()
        def resource = client.resource("http://localhost:8080/players")
      when:
        def response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(List.class)
      then:
        response == []
    }
}

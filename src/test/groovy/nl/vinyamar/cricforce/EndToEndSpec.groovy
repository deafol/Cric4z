package nl.vinyamar.cricforce

import com.sun.jersey.api.client.Client
import io.dropwizard.cli.ServerCommand
import io.dropwizard.testing.junit.DropwizardAppRule
import nl.vinyamar.cricforce.backend.CricForceApplication
import nl.vinyamar.cricforce.backend.CricForceConfiguration
import nl.vinyamar.cricforce.backend.config.Test
import nl.vinyamar.cricforce.backend.domain.Player
import org.junit.ClassRule
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.MediaType

class EndToEndSpec extends Specification {

    @Shared
    def jdbcTemplate

    @ClassRule
    public static final RULE =
            new DropwizardAppRule<CricForceConfiguration>(CricForceApplication.class, new ClassPathResource("test.yml").path);

    def setup() {
        jdbcTemplate = new JdbcTemplate(CricForceConfiguration.dataSource)
    }

    def "return a list with all playernames in JSON"() {
      setup:
        setupTestPlayers()
        CricForceApplication.main("server")
        def client = new Client()
        def resource = client.resource("http://localhost:8080/players")
      when:
        def response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(List.class)
      then:
        response == ["M de Vries", "RQ  Prenen"]
    }

    def setupTestPlayers() {
        def player1 = new Player([firstName: "Milan", lastName: "Vries", prefix: "de", initials: "M"])
        def player2 = new Player([firstName: "Robert", lastName: "Prenen", prefix: "", initials: "RQ"])

        jdbcTemplate.execute("insert into Player values (1, '$player1.firstName', '$player1.lastName', '$player1.prefix', '$player1.initials')")
        jdbcTemplate.execute("insert into Player values (2, '$player2.firstName', '$player2.lastName', '$player2.prefix', '$player2.initials')")
    }
}

package nl.vinyamar.cricforce.backend

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.WebResource
import groovy.text.GStringTemplateEngine
import nl.vinyamar.cricforce.backend.domain.Player
import nl.vinyamar.cricforce.backend.util.NetUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.core.MediaType

class EndToEndSpec extends Specification {

    @Shared
    JdbcTemplate jdbcTemplate
    @Shared
    def application
    @Shared
    def appPort
    @Shared
    def adminPort

    def setupSpec() {
        getFreePorts()
        application = new CricForceApplication()
        application.run("server", createTestYml())
        jdbcTemplate = new JdbcTemplate(application.context.getBean("dataSource"))
    }

    private ArrayList<Integer> getFreePorts() {
        def freePorts = NetUtils.freePorts(1);
        appPort = freePorts[0]
        adminPort = freePorts[1]
    }

    private createTestYml() {
        def binding = ["applicationPort": appPort, "adminPort": adminPort]
        def templateFile = new File(new ClassPathResource("test.yml").file.absolutePath)
        def fileContents = new GStringTemplateEngine().createTemplate(templateFile).make(binding).toString()
        File tempFile = File.createTempFile('test', '.yml')
        tempFile.deleteOnExit()
        tempFile.write fileContents
        tempFile.absolutePath
    }

    def "return a list with all playernames in JSON"() {
      setup:
        setupTestPlayers()
      when:
        def response = playerResource().accept(MediaType.APPLICATION_JSON_TYPE).get(List.class)
      then:
        response == ["M de Vries", "RQ  Prenen"]
    }

    private WebResource playerResource() {
        new Client().resource("http://localhost:$appPort/players")
    }

    private setupTestPlayers() {
        def player1 = new Player([firstName: "Milan", lastName: "Vries", prefix: "de", initials: "M"])
        def player2 = new Player([firstName: "Robert", lastName: "Prenen", prefix: "", initials: "RQ"])

        jdbcTemplate.execute "insert into Player values (1, '$player1.firstName', '$player1.lastName', '$player1.prefix', '$player1.initials')"
        jdbcTemplate.execute "insert into Player values (2, '$player2.firstName', '$player2.lastName', '$player2.prefix', '$player2.initials')"
    }
}

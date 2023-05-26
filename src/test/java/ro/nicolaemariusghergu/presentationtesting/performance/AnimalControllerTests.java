package ro.nicolaemariusghergu.presentationtesting.performance;

import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.feeder.BatchableFeederBuilder;
import io.gatling.http.Predef;
import io.gatling.http.protocol.HttpProtocolBuilder;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpDsl;

import java.util.function.Function;


public class AnimalControllerTests extends Simulation {

    private HttpProtocolBuilder httpProtocol = Predef.http(GatlingConfiguration.loadForTest())
            .baseUrl("http://localhost:8080");

//    FeederBuilder<String> feeder = csv("search.csv").random();

    static final class Templates {
        public static final Function<Session, String> template = session -> {
            String name = "Name Load Test";
            String race = "Race Load Test";
            return "{ \"name\": \"" + name + "\", \"race\": \"" + race + "\" }";
        };
    }

    private ScenarioBuilder scenario = CoreDsl.scenario("Animals - Load Testing")
            .exec(HttpDsl.http("Get all animals")
                    .get("/api/animals/all"))
            .pause(1)
            .exec(HttpDsl.http("Post new animal")
                    .post("/api/animals")
                    .header("Content-Type", "application/json")
                    .body(CoreDsl.StringBody(Templates.template)));

    {
        setUp(
                scenario.injectOpen(
                        OpenInjectionStep.atOnceUsers(50),
                        CoreDsl.rampUsers(50).during(10),
                        CoreDsl.constantUsersPerSec(40).during(10).randomized()
                ).protocols(() -> httpProtocol.protocol())
        );
    }
}
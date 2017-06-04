package ngSpring.demo.integration.steps;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.apache.commons.httpclient.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.given;

/**
 * @author mreinhardt
 */
// tag::serenity-rest-test[]
public class EventSteps {

  @Step
  public RequestSpecification login(String username, String password) {
    return given()
      .authentication().basic(username, password);
  }


  @StepGroup
  public Response getEvent(String username, String password, String eventId) {
    return login(username, password)
      .expect()
      .statusCode(HttpStatus.SC_OK)
      .when()
      .get("/api/events/{eventId}", eventId);
  }

  @Step
  public Response getEvents(String username, String password) {
    return login(username, password)
      .expect()
      .statusCode(HttpStatus.SC_OK)
      .when()
      .get("/api/events/");
  }
}

// end::serenity-rest-test[]

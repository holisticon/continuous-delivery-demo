package ngSpring.demo.integration.steps;

import com.google.inject.Inject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.configuration.SystemPropertiesConfiguration;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.Before;

/**
 * @author mreinhardt
 */
// tag::serenity-rest-test[]
public class EventSteps {

  // end::serenity-rest-test[]
  @Inject
  public SystemPropertiesConfiguration systemPropertiesConfiguration;
  // tag::serenity-rest-test[]

  @StepGroup
  public Response getEvent(String username, String password, String eventId) {
    return RestAssured.given()
      .with().baseUri(getBaseUrl()).basePath("")
      .auth().basic(username, password)
      .expect()
      .statusCode(HttpStatus.SC_OK)
      .when()
      .get("/api/events/{eventId}", eventId);
  }

  @Step
  public Response getEvents(String username, String password) {
    return RestAssured.given()
      .with().baseUri(getBaseUrl()).basePath("")
      .auth().basic(username, password)
      .expect()
      .statusCode(HttpStatus.SC_OK)
      .when()
      .get("/api/events/");
  }

  // end::serenity-rest-test[]

  @Before
  public String getBaseUrl() {
    String baseurl = "http://localhost:9080";
    if (this.systemPropertiesConfiguration != null && this.systemPropertiesConfiguration.getBaseUrl() != null) {
      baseurl = this.systemPropertiesConfiguration.getBaseUrl();
    }
    return baseurl;
  }

  // tag::serenity-rest-test[]
  // end::serenity-rest-test[]
}

package ngSpring.demo.integration.rest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import ngSpring.demo.integration.steps.EventSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author mreinhardt
 */
// tag::serenity-rest-test[]
@RunWith(SerenityRunner.class)
public class EventControllerIT {

  @Steps
  public EventSteps eventSteps;

  @WithTags({
    @WithTag(type = "feature", name = "Event API"),
  })
  @Test
  public void shouldListEvent() {
    eventSteps.getEvents("user", "password");
  }

  @WithTags({
    @WithTag(type = "feature", name = "Event API"),
  })
  @Test
  public void shouldGetOneEvent() {
    eventSteps.getEvent("user", "password", "4");
  }

}
// end::serenity-rest-test[]

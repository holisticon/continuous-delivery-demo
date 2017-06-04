package ngSpring.demo.integration.ui;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import ngSpring.demo.integration.steps.LoginSteps;
import ngSpring.demo.integration.ui.util.AbstractSerenityITTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author mreinhardt
 */
// tag::serenity-ui-test[]
@WithTags({
  @WithTag(type = "epic", name = "User management")
})
@Narrative(text = "Login")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "testdata/login.csv")
public class LoginIT extends AbstractSerenityITTestBase {

  @Steps
  public LoginSteps loginSteps;

  // test data

  private String username;

  private String password;

  private boolean fail;

  @Qualifier
  public String qualifier() {
    return "user " + username;
  }

  @WithTags({
    @WithTag(type = "feature", name = "Login"),
    @WithTag(type = "feature", name = "User"),
    @WithTag(type = "testtype", name = "postdeploy")
  })
  @Test
  @Issues(value = {"#9"})
  public void loginShouldWork() {
    loginSteps.performLogin(this.username, this.password);
    loginSteps.userShouldSeeNoErrorMessage();
  }
}
// end::serenity-ui-test[]

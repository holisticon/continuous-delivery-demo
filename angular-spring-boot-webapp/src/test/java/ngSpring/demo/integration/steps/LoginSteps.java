package ngSpring.demo.integration.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import ngSpring.demo.integration.pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author mreinhardt
 */
// tag::serenity-ui-test[]
public class LoginSteps extends ScenarioSteps {
// end::serenity-ui-test[]

    private static final long serialVersionUID = -566491664850250304L;

    public LoginSteps(Pages pages) {
        super(pages);
    }
    // PAGES
    // tag::serenity-ui-test[]
    public LoginPage loginPage() {
        return getPages().currentPageAt(LoginPage.class);
    }

    // STEPS
    @Step
    public void inputUsername(String user) {
        loginPage().inputUserName(user);
    }

    @Step
    public void inputPassword(String pass) {
        loginPage().inputPassword(pass);
    }

    @Step
    public void clickOnLogin() {
        loginPage().clickOnLogin();
    }

    @StepGroup
    public void performLogin(String email, String pass) {
        inputUsername(email);
        inputPassword(pass);
        clickOnLogin();
    }

    @Step
    public void userShouldSeeLogin() {
        assertThat("Should be on login page ", loginPage().loginFormVisible());
    }


    @Step
    public void userShouldSeeAnErrorMessage() {
        assertThat("There should be an error message ", loginPage().errorMessageIsVisible());
        assertThat("There should be only one info message ", loginPage().onlyOneErrorMessageIsVisible());
    }
    // end::serenity-ui-test[]

    @Step
    public void userShouldSeeNoErrorMessage() {
        assertThat("There should be no error message ", !loginPage().errorMessageIsVisible());
    }
    // tag::serenity-ui-test[]
}

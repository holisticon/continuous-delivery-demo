package ngSpring.demo.integration.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author mreinhardt
 */
@DefaultUrl("http://localhost:9000/login")
// tag::serenity-ui-test[]
public class LoginPage extends CmsPage {
    // end::serenity-ui-test[]

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // tag::serenity-ui-test[]

    @FindBy(id = "username")
    private WebElement userInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;


    public void inputUserName(String emailAddress) {
        enterText(userInput, emailAddress);
    }

    public void inputPassword(String password) {
        enterText(passwordInput, password);
    }

    public void clickOnLogin() {
        click(loginButton);
    }

    public boolean loginFormVisible() {
        return element(userInput).isCurrentlyVisible() && element(passwordInput).isCurrentlyVisible();
    }
}
// end::serenity-ui-test[]

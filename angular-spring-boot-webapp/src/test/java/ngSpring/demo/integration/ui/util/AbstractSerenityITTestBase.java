package ngSpring.demo.integration.ui.util;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSerenityITTestBase {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSerenityITTestBase.class);

  @Managed
  public WebDriver webdriver;

  @ManagedPages
  public Pages pages;

  @Before
  public void setup() {
    LOGGER.debug("Going to use driver " + webdriver.toString());
  }
}

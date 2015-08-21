import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
  	goTo("http://localhost:4567/");
  	assertThat(pageSource()).contains("Create Your Dictionary");
  }

  @Test
  public void wordsAreDisplayedOnDictionaryPageTest() {
    goTo("http://localhost:4567/");
    click("button", withText("View Words"));
    assertThat(pageSource()).contains("Your Dictionary");
  }

  @Test
  public void goBackButtonReturnsToIndexTest() {
    goTo("http://localhost:4567/dictionary");
    click("button", withText("Go Back"));
    assertThat(pageSource()).contains("Create Your Dictionary");
  }

  @Test
  public void addWordPageDisplaysTest() {
    goTo("http://localhost:4567/");
    click("button", withText("Add New Word"));
    assertThat(pageSource()).contains("Add A New Word");
  }
}

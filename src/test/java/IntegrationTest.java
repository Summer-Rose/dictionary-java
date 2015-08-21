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
  	assertThat(pageSource()).contains("Your Dictionary");
  }

  @Test
  public void addANewWordButtonBringsToAddWordFormTest() {
    goTo("http://localhost:4567/");
    click("button", withText("Add New Word"));
    assertThat(pageSource()).contains("Add A New Word");
  }

  // @Test
  // public void goBackButtonReturnsToIndexTest() {
  //   goTo("http://localhost:4567/dictionary/new");
  //   click("button", withText("Go Home"));
  //   assertThat(pageSource()).contains("Your Dictionary");
  // }

  @Test
  public void addWordSubmitBringsToSuccesPage() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    assertThat(pageSource()).contains("Your word has been added");
  }

  // @Test
  // public void definitionPageDisplaysWordTest() {
  //   goTo("http://localhost:4567/");
  //   click("button", withText("Add New Word"));
  //   fill("#word").with("Awesome Sauce");
  //   click("button", withText("Add"));
  //   click("button", withText("View Dictionary"));
  //   click("button", withText("View Definitions"));
  //   assertThat(pageSource()).contains("Awesome Sauce");
  // }
  //
  // @Test
  // public void definitionsDisplayedOnDefinitionsPage() {
  //   goTo("http://localhost:4567/");
  //   click("button", withText("Add New Word"));
  //   fill("#word").with("Summer");
  //   click("button", withText("Add"));
  //   click("button", withText("Home"));
  //   assertThat(pageSource()).contains("Summer");

    // click("button", withText("View Definitions"));
    // click("button", withText("Add Definition"));
    // fill("#definition").with("Coolest Person Ever");
    // submit(".btn");
    // click("button", withText("View Dictionary"));
    // click("button", withText("View Definitions"));
    // assertThat(pageSource()).contains("Coolest Person Ever");

}

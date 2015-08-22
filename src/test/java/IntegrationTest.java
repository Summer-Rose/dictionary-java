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

  @Test
  public void goBackButtonReturnsToIndexTest() {
    goTo("http://localhost:4567/dictionary/new");
    click("button", withText("Go Back"));
    assertThat(pageSource()).contains("Your Dictionary");
  }

  @Test
  public void addWordSubmitBringsToSuccesPage() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    assertThat(pageSource()).contains("Your word has been added");
  }

  @Test
  public void addedWordIsDisplayedOnMainPage() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    click("button", withText("Go Home"));
    assertThat(pageSource()).contains("Summer");
  }

  @Test
  public void multipleAddedWordsAreDisplayedOnMainPage() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    click("button", withText("Add Another Word"));
    fill("#word").with("Awesome Sauce");
    click("button", withText("Add"));
    click("button", withText("Go Home"));
    assertThat(pageSource()).contains("Summer");
    assertThat(pageSource()).contains("Awesome Sauce");
  }

  @Test
  public void viewDefinitionsPageDispalysWordDefinition() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    click("button", withText("Go Home"));
    click("button", withText("View Definitions"));
    click("button", withText("Add Definition"));
    fill("#definition").with("Coolest person ever");
    submit(".btn");
    click("button", withText("Go Home"));
    click("button", withText("View Definitions"));
    assertThat(pageSource()).contains("Coolest person ever");
  }
  @Test
  public void definitionAddedSuccessPageDisplaysCorrectMessage() {
    goTo("http://localhost:4567/dictionary/new");
    fill("#word").with("Summer");
    click("button", withText("Add"));
    click("button", withText("Go Home"));
    click("button", withText("View Definitions"));
    click("button", withText("Add Definition"));
    fill("#definition").with("Coolest person ever");
    submit(".btn");
    assertThat(pageSource()).contains("Your definition has been added");
  }
}

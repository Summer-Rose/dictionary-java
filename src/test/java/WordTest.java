import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  // @Rule
  // public ClearRule clearRule = new ClearRule();

  @Test
  public void Word_instantiatesCorrectly_true() {
    Word testWord = new Word("Summer");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  public void Word_getsCorrectWord_Summer() {
    Word testWord = new Word("Summer");
    assertEquals("Summer", testWord.getWord());
  }

  @Test
  public void Word_returnsWordId() {
    Word testWord = new Word("Summer");
    assertTrue(Word.all().size() == testWord.getId());
  }
}

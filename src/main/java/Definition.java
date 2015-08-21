import java.time.LocalDateTime;
import java.util.ArrayList;

public class Definition {
  private static ArrayList<Definition> instances = new ArrayList<Definition>();
  private String mDefinition;
  private int mId;

  public Definition(String definition) {
    mDefinition = definition;
  }
}

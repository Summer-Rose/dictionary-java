import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {

	@Rule
	public ClearRule clearRule = new ClearRule();

	@Test
	public void Definition_instantiatesCorrectly_true() {
		Definition testDefinition = new Definition("The coolest person and hottest season");
		assertEquals(true, testDefinition instanceof Definition);
	}


}

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * InterpreterTest Class
 * - Unit test for Interpreter class
 * 
 * @author Lawrence Wong
 * @since Feb 21, 2016
 * @version 1.0
 */

public class InterperterTest {
	@Test
	public void testInterperterConstructor()
	{
		Interpreter interp = new Interpreter();
		assertNull(interp.getCleanInput());
	}
	
	@Test
	public void testParserWithParameter(){
		Interpreter interp = new Interpreter("   A d d(   2, 3  )        ");
		assertNotNull(interp.getCleanInput());
		String expectedStr = new String("add(2,3)");
		assertEquals(expectedStr.length(), interp.getCleanInput().length());
		assertEquals(expectedStr, interp.getCleanInput());
		assertNotEquals("Add(2,3)", interp.getCleanInput());
		assertNotEquals("   A d d(   2, 3  )        ", interp.getCleanInput());
		assertNotEquals("Add(2, 3)", interp.getCleanInput());
	}
}

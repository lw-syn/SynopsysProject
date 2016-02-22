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
	
	@Test 
	public void testParserErrInputArgument1(){
		Interpreter interp = new Interpreter("  Add (2 + 3 )");
		assertEquals(Interpreter.ERR_INPUT_ARGUMENT, interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserErrInputArgument2(){
		Interpreter interp = new Interpreter("Add )2 , 3 )");
		assertEquals(Interpreter.ERR_NO_MATCH_BRACKET, interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserErrInputArgument3(){
		Interpreter interp = new Interpreter("  Sqr (2 , 3 )");
		assertEquals(Interpreter.ERR_UNKNOWN_OPERATOR, interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserErrInputArgument4(){
		Interpreter interp = new Interpreter("Div (2 ,0 )");
		assertEquals(Interpreter.ERR_DIV_ZERO, interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserErrInputArgument5(){
		Interpreter interp = new Interpreter();
		assertEquals(Interpreter.ERR_INCORRECT_FORMAT_STR, interp.parserInputString(null));
	}
	
	@Test 
	public void testParserResult1(){
		Interpreter interp = new Interpreter("add(1, 2)");
		assertEquals("result = 3.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult2(){
		Interpreter interp = new Interpreter("add(1, mult(2, 3))");
		assertEquals("result = 7.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult3(){
		Interpreter interp = new Interpreter("mult(add(2, 2), div(9, 3))");
		assertEquals("result = 12.0", interp.parserInputString(interp.getCleanInput()));
	}

	@Test 
	public void testParserResult4(){
		Interpreter interp = new Interpreter("let(a, 5, add(a, a))");
		assertEquals("result = 10.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult5(){
		Interpreter interp = new Interpreter("let(a, 5, let(b, mult(a, 10), add(b, a)))");
		assertEquals("result = 55.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult6(){
		Interpreter interp = new Interpreter("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
		assertEquals("result = 40.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult7(){
		Interpreter interp = new Interpreter("mult(add(let(b,2,add(b,b)), 2), div(9, 3))");
		assertEquals("result = 18.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test 
	public void testParserResult8(){
		Interpreter interp = new Interpreter("div(add(let(b,2,add(b,b)), 2), div(9, 3))");
		assertEquals("result = 2.0", interp.parserInputString(interp.getCleanInput()));
	}
	
	@Test
	public void testOperator(){
		Interpreter interp = new Interpreter("multadd(2,3)");
		assertEquals(Interpreter.ERR_UNKNOWN_OPERATOR, interp.parserInputString(interp.getCleanInput()));
	}
}

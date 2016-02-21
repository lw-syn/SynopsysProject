/**
 * Calculator Class - Get the input argument from user
 * Create the Interpreter object to parse the arithmetic operation
 * 
 * Details:
 * An expression is one of the of the following:
 *         Numbers: integers between Integer.MIN_VALUE and Integer.MAX_VALUE
 *         Variables: strings of characters, where each character is one of a-z, A-Z
 *         Arithmetic functions: add, sub, mult, div, each taking two arbitrary expressions as arguments.  In other words, each argument may be any of the expressions on this list.
 *         A “let” operator for assigning values to variables:
 *let(<variable name>, <value expression>, <expression where variable is used>)
 *As with arithmetic functions,  the value expression and the expression where the variable is used may be an arbitrary expression from this list. 
 *e.g. 	add(1, 2) = 3
 *		add(1, mult(2, 3)) = 7
 *		mult(add(2, 2), div(9, 3)) = 12
 *		let(a, 5, add(a, a)) = 10
 *		let(a, 5, let(b, mult(a, 10), add(b, a))) = 55
 *		let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))) = 40
 *
 *Implement a logging layer to log all relevant information.  Manage at least 3 levels of verbosity:
 *INFO, ERROR, and DEBUG.  Allow Verbosity to be set via a command-line option.
 *
 * @author Lawrence Wong
 * @since Feb 20, 2016
 * @version 1.0
 */

public class Calculator {

	public static void main(String[] args) {
		//loop through all the argument and calculate the result of arithmetic operation
		for (String input: args) {
			if (input != null){
				Interpreter interpreter = new Interpreter(input);
				input = interpreter.getCleanInput();
			    System.out.println("Expression = " + input);
			}
		}
	}
}

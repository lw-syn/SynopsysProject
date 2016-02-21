/**
 * Interpreter Class
 * - Parsing the input string from user and calculate the result from the arithmetic expression
 * 
 * @author Lawrence Wong
 * @since Feb 21, 2016
 * @version 1.0
 */

public class Interpreter {

	private String _inputNoSpace;
	
	/**
	 * Default constructor
	 */
	Interpreter(){
    	_inputNoSpace = null;
    }
	
	/**
     * Overloaded Constructor of Interpreter class.
     * 1. It trims out all the leading and trailing space.
     * 2. It removes any space inside the string.
     * 3. It converts all the characters to lower case. 
     * @param input - input argument from the user.
     */
    Interpreter(String input){
		// Remove all the white spaces and tab character
    	// change all the characters in the string to lower cases
    	_inputNoSpace = input.trim();
		_inputNoSpace = _inputNoSpace.replaceAll("\\s", "");
		_inputNoSpace = _inputNoSpace.toLowerCase();
    }
	
    /**
     * Getter function - of the string for calculation.
     * @return - retrieve the _inputNoSpace.
     */
    public String getCleanInput(){
    	return _inputNoSpace;
    }
}

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
	
	public final static String ERR_EMPTY_STR = "Input String is empty!";
    public final static String ERR_INPUT_ARGUMENT = "Error in the argument!";
    public final static String ERR_DIV_ZERO = "Denominator is zero of division!";
    public final static String ERR_UNKNOWN_OPERATOR = "Unknown Operator!";
    private final String ADD = "add";
	private final String SUB = "sub";
	private final String MULT = "mult";
	private final String DIV = "div";
	private final String LET = "let";
	
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
    
    /**
     * doCalculation function.
     * Find the arithmetic operator from the str and perform the calculation
     * @param str - string contain the operator
     * @param first - double
     * @param second - double
     * @return the String of the result
     */
    private String doCalculation(String str, int first, int second){
    	// Create the calculation object and store the first and second number
    	Calculation cal = new Calculation(first, second);
    	String result = new String("");
    	// perform different arithmetic operation and convert it to a string
		if (str.startsWith(ADD)){
		    result = Integer.toString(cal.add());
		}else if (str.startsWith(SUB)){
			result = Integer.toString(cal.sub());
		}else if (str.startsWith(MULT)){
			result = Integer.toString(cal.mult());
		}else if (str.startsWith(DIV)){
			double val = cal.div();
			if (val != Double.NaN)
				result = Double.toString(cal.div());
			else
				result = ERR_DIV_ZERO;
		}
		return result;
    }
}

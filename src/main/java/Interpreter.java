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
	
	public final static String ERR_INCORRECT_FORMAT_STR = "Input String is incorrect format!";
	public final static String ERR_INPUT_ARGUMENT = "Error in the argument!";
	public final static String ERR_NO_MATCH_BRACKET = "No Matching bracket!";
	public final static String ERR_MISLOCATE_OPEN_CLOSE_BRACKET = "Mislocate open and close brackets!";
    public final static String ERR_DIV_ZERO = "Denominator is zero of division!";
    public final static String ERR_UNKNOWN_OPERATOR = "Unknown Operator!";
    private final String ADD = "add";
	private final String SUB = "sub";
	private final String MULT = "mult";
	private final String DIV = "div";
	private final String LET = "let";
	private final char OPENBRACKET = '(';
	private final char CLOSEBRACKET = ')';
	private final char COMMA = ',';
	
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
    private String doCalculation(String str, double first, double second){
    	// Create the calculation object and store the first and second number
    	Calculation cal = new Calculation(first, second);
    	String result = new String("");
    	int inputOpenBracketIndex = str.indexOf(OPENBRACKET);
    	String operator = str.substring(0, inputOpenBracketIndex);
    	// perform different arithmetic operation and convert it to a string
		if (operator.equalsIgnoreCase(ADD)){
		    result = Double.toString(cal.add());
		}else if (operator.equalsIgnoreCase(SUB)){
			result = Double.toString(cal.sub());
		}else if (operator.equalsIgnoreCase(MULT)){
			result = Double.toString(cal.mult());
		}else if (operator.equalsIgnoreCase(DIV)){
			double val = cal.div();
			if (val != Double.NaN)
				result = Double.toString(cal.div());
			else
				result = ERR_DIV_ZERO;
		}
		else{
			result = ERR_UNKNOWN_OPERATOR;
		}
		return result;
    }
    
    /**
     * parserInputString function.
     * This function is used to parser the string input recursively
     * Method: 
     * 1. Find the first pair of matching open and close bracket.
     * 2. Locate and isolate the simple expression. 
     * 3. Evaluate the simple expression.
     * 4. if successful, replace the value with the expression and call parserInputString recursively for further parsing
     * 5. if unsuccessful, it contains variable and let statement.  This will be handle by the function: HandleLetKeyword()
     * @param inputStr
     */
    public String parserInputString(String inputStr){
    	// Make sure the inputStr is not null
    	if (inputStr == null){
    		return ERR_INCORRECT_FORMAT_STR;
    	}
    	// Forward scan the inputStr and find the locations of the first matching 
    	// open and close brackets pair in the expression
    	// e.g. add(1, mult(2, 3))
    	// The first matching open and close brackets is before 2 and after 3
    	int inputCloseBracketIndex = -1;
    	int inputOpenBracketIndex = inputStr.indexOf(OPENBRACKET);
    	for (int i=0; i<inputStr.length(); i++){
    		if (inputStr.charAt(i) == OPENBRACKET){
    			inputOpenBracketIndex = i;
    		}else if (inputStr.charAt(i) == CLOSEBRACKET){
    			inputCloseBracketIndex = i;
    			break;
    		}
    	}
    	// Check the index of open and close brackets
    	// case 1: if the open or close bracket index is -1, it means no bracket pair in the expression
    	// case 2: if the open bracket index is larger than close bracket index, it means the expression format is incorrect 
    	if (inputOpenBracketIndex > inputCloseBracketIndex)
    		return ERR_MISLOCATE_OPEN_CLOSE_BRACKET;
    	if ((inputCloseBracketIndex == -1) || 
    		(inputOpenBracketIndex == -1)){
    		return ERR_NO_MATCH_BRACKET;
    	}
    	String subStr = new String("");
    	int lastOpenBracketOnLeft = -1;
    	// Backward scan the location of the comma from the open bracket location
    	// If we cannot find the comma, backward scan the location of another open bracket from the first open bracket location
    	// If we cannot find both comma and another open bracket on the left, it means the inputStr carries only one simple expression e.g. add(1,2)
    	// If we find the comma, get the substring from the right of the comma to the close bracket
    	// If we find another open bracket on the left, get the substring from the right of the comma to the close bracket
    	int inputLastCommaIndex = inputStr.lastIndexOf(COMMA, inputOpenBracketIndex);
    	if (inputLastCommaIndex == -1){
    		lastOpenBracketOnLeft = inputStr.lastIndexOf(OPENBRACKET, inputOpenBracketIndex-1);
    		if (lastOpenBracketOnLeft == -1){
    			subStr = inputStr;
    		}else{
    			subStr = inputStr.substring(lastOpenBracketOnLeft+1, inputCloseBracketIndex+1);
    		}
    	}else{
    		subStr = inputStr.substring(inputLastCommaIndex+1, inputCloseBracketIndex+1);
    	}
    	// This subStr will contain only the simple express for calculation
    	int subCommaIndex = subStr.indexOf(COMMA);
    	int subOpenBracketIndex = subStr.indexOf(OPENBRACKET);
    	int subCloseBracketIndex = subStr.indexOf(CLOSEBRACKET);
    	// Find the first and second numbers
    	if ((subCommaIndex == -1) || (subOpenBracketIndex == -1) || (subCloseBracketIndex == -1)){
    		System.out.println("Error in the argument!");
    		return ERR_INPUT_ARGUMENT;
    	}
    	String first = subStr.substring(subOpenBracketIndex+1, subCommaIndex);
    	String second = subStr.substring(subCommaIndex+1, subCloseBracketIndex);
    	double firstNumber = 0.0;
    	double secondNumber = 0.0;
    	String newStr = new String("");
    	// Convert the first and second number string to double
    	try{
    		firstNumber = Double.parseDouble(first);
    		secondNumber = Double.parseDouble(second);
    		int newIndex = -1;
        	String result = doCalculation(subStr, firstNumber, secondNumber);
        	if (result.equals(Double.toString(Double.NaN))){
        		return ERR_DIV_ZERO;
        	}
        	if (result.isEmpty()){
        		return ERR_UNKNOWN_OPERATOR;
        	}
        	try{
        		Double.parseDouble(result);
        	}catch(NumberFormatException e){
        		return result;
        	}
        	if ((inputLastCommaIndex == -1) && (lastOpenBracketOnLeft == -1)){
        		return ("result = " + result);
        	}else{
        		if (inputLastCommaIndex == -1){
        			newIndex = lastOpenBracketOnLeft;
        		}else{
        			newIndex = inputLastCommaIndex;
        		}
        	}
        	if (newIndex != -1){
    			String firstHalfStr = inputStr.substring(0, newIndex+1);
    			String secondHalfStr = inputStr.substring(newIndex+subStr.length()+1);
    			newStr = firstHalfStr + result + secondHalfStr;
        	}
    	}catch (NumberFormatException e){
    		// if we catch any exception here, it means it contain let keyword.
    		newStr = HandleLetKeyword(inputStr);
    	}
    	// Call parserInputString again for further parsing
    	return(parserInputString(newStr));
    }
    
    /**
     * HandleLetKeyword function.
     * Scan backward to find the first let keyword from the end of string.
     * Find the variable and the value expression.
     * Replace variable with the value expression from the string
     * @param inputStr
     * @return the new string after the replacement
     */
    private String HandleLetKeyword(String inputStr){
    	// Make sure inputStr is not null
    	if (inputStr == null){
    		return null;
    	}
    	// Backward scan to find the location of the let keyword
    	int letLocation = inputStr.lastIndexOf(LET);
    	// Find the location of first comma from let keyword
    	if (letLocation == -1){
    		return null;
    	}
    	int firstComma = inputStr.indexOf(COMMA, letLocation);
    	if (firstComma == -1){
    		return null;
    	}
    	// Find the variable
    	String varStr = inputStr.substring(letLocation + LET.length()+1, firstComma);
    	if (varStr.length() == 0)
    		return null;
    	int secondComma = inputStr.indexOf(COMMA, firstComma+1);
    	if (secondComma == -1){
    		return null;
    	}
    	// Find the value expression
    	String valStr = inputStr.substring(firstComma+1, secondComma);
    	int closeBracket = -1;
    	String expression = new String ("");
    	try{
    		// Check whether the valStr is actual a number
    		Double.parseDouble(valStr);
    		closeBracket = inputStr.indexOf(CLOSEBRACKET, secondComma+1);
    		if (closeBracket == -1){
    			return null;
    		}
    		expression = inputStr.substring(secondComma+1, inputStr.length());
    		expression = adjustExpression(expression);
    	}catch(NumberFormatException e){
    		// It means the valStr is not a number, need further analysis of the string
    		int thirdComma = inputStr.indexOf(COMMA, secondComma+1);
    		if (thirdComma == -1){
    			return null;
    		}
    		valStr = inputStr.substring(firstComma+1, thirdComma);
    		closeBracket = inputStr.indexOf(CLOSEBRACKET, thirdComma+1);
    		if (closeBracket == -1){
    			return null;
    		}
    		expression = inputStr.substring(thirdComma+1, closeBracket+1);
    	}
    	// Replace the variable with value expression
    	int loc = expression.indexOf(varStr);
    	while(loc != -1){
    		// If it is an arithmetic expression, skip it - don't replace 
    		if (!isOperator(expression, loc)){
    			expression = expression.substring(0, loc) + valStr + 
    					expression.substring(loc+varStr.length(), expression.length());
    		}
    		loc = expression.indexOf(varStr, loc+1);
    	}   
    	// Insert the expression to replace the let keyword expression
		String firstHalfStr = inputStr.substring(0, letLocation);
		String secondHalfStr = new String("");
		int endComma = inputStr.indexOf(COMMA, closeBracket+1);
		if (endComma != -1){
			secondHalfStr = inputStr.substring(endComma, inputStr.length());
		}else{
			secondHalfStr = inputStr.substring(closeBracket+1, inputStr.length()-1);
		}
		String newStr;
		if ((!firstHalfStr.isEmpty()) && (!secondHalfStr.isEmpty())){
			newStr = firstHalfStr + expression + secondHalfStr;
		}else{
			newStr = expression;
		}
		return newStr;
    }
    /**
     * isOperator function.
     * Check whether the string is an operator.
     * If yes, return true. Otherwise, return false.
     * @param str
     * @param loc
     * @return
     */
    private boolean isOperator(String str, int loc){
    	// Make sure the search location is within the length of string
    	if ((loc + ADD.length() > str.length()) ||
    		(loc + SUB.length() > str.length()) ||
    		(loc + MULT.length() > str.length()) ||
    		(loc + DIV.length() > str.length())){
    			return false;
    	}
    	// Check whether it contain arithmetic operator
    	if ((str.substring(loc, loc+ADD.length()).equals(ADD)) ||
    		(str.substring(loc, loc+SUB.length()).equals(SUB)) ||
    		(str.substring(loc, loc+DIV.length()).equals(DIV)) ||
    		(str.substring(loc, loc+MULT.length()).equals(MULT))){
    			return true;
    	}
    	return false;
    }
    
    /**
     * adjustExpression function.
     * Trim the string with correct ending of closing bracket
     * @param str
     * @return
     */
    private String adjustExpression(String str){
    	String retStr = new String("");
    	int numOpenBracket = 0;
    	int indCloseBracket = 0;
    	for (int a=0; a<str.length(); a++){
    		if (str.charAt(a) == OPENBRACKET){
    			numOpenBracket++;
    		}
    		if (str.charAt(a) == CLOSEBRACKET){
    			indCloseBracket = a;
    			break;
    		}
    	}
    	// Find the matching brackets of the expression and trim off the rest of string
    	if (numOpenBracket == 1){
    		retStr = str.substring(0, indCloseBracket+1);
    	}else{
	    	int findCloseBracket = 1;
	    	for (int a=indCloseBracket+1; a<str.length(); a++){
	    		if (str.charAt(a) == CLOSEBRACKET){
	    			findCloseBracket++;
	    		}
	    		if (numOpenBracket == findCloseBracket){
	    			retStr = str.substring(0, a+1);
	    			break;
	    		}
	    	}
    	}
    	return retStr;
    }
}

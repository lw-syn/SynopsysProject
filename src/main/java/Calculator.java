import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
 *If user type INFO as parameter, it will print all the successful calculations ever run from this Calculator
 *If user type ERROR as parameter, it will print all the failure calculations ever run from this Calculator
 *If user type DEBUG as parameter, it will print all the failure calculations with proper error ever run from this Calculator
 *
 * @author Lawrence Wong
 * @since Feb 20, 2016
 * @version 1.0
 */

public class Calculator {
	
	private final static String errorFile = "error.txt";
	private final static String infoFile = "info.txt";
	private final static String debugFile = "debug.txt";
	private final static String INFO = "INFO";
	private final static String ERROR = "ERROR";
	private final static String DEBUG = "DEBUG";
	private final static String errorLog = "This is error log";
	private final static String infoLog = "This is info log";
	private final static String debugLog = "This is debug log";

	public static void main(String[] args) {
		
		// Create the Info, Error and Debug files
		File fileError = new File(errorFile);
		File fileInfo = new File(infoFile);
		File fileDebug = new File(debugFile);
		try {
			if (fileInfo.createNewFile()){
				Files.write(Paths.get(infoFile), String.format("%s%n", infoLog).getBytes(), StandardOpenOption.APPEND);
			}
			if (fileError.createNewFile()){
				Files.write(Paths.get(errorFile), String.format("%s%n", errorLog).getBytes(), StandardOpenOption.APPEND);
			}
			if (fileDebug.createNewFile()){
				Files.write(Paths.get(debugFile), String.format("%s%n", debugLog).getBytes(), StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
		    System.out.println(String.format("Fail to create the %s and %s files", infoFile, errorFile));
		}
		// If the only 1 argument and it is either INFO, ERROR, or DEBUG, Display the log files respectively
		if (args.length == 1){
			if (args[0].equals(INFO)){
				displayFile(infoFile);
			}else if (args[0].equals(ERROR)){
				displayFile(errorFile);
			}else if (args[0].equals(DEBUG)){
				displayFile(debugFile);
			}				
		}
		//loop through all the argument and calculate the result of arithmetic operation
		for (String input: args) {
			if (input != null){
				if ((!input.equals(INFO)) &&
					(!input.equals(ERROR) &&
					(!input.equals(DEBUG)))){
					// If it is not INFO, ERROR, DEBUG
					Interpreter interpreter = new Interpreter(input);
					String inputNoSpace = interpreter.getCleanInput();
					System.out.println("Expression = " + inputNoSpace );
					String result = interpreter.parserInputString(inputNoSpace);
					String msg = String.format("%s : %s%n", input, result);
					if ((result.equals(Interpreter.ERR_DIV_ZERO)) ||
					  	(result.equals(Interpreter.ERR_INCORRECT_FORMAT_STR)) ||
					   	(result.equals(Interpreter.ERR_INPUT_ARGUMENT)) ||
					   	(result.equals(Interpreter.ERR_UNKNOWN_OPERATOR))){
					   	// If error, write it to error and debug file
					   	try{
					   		Files.write(Paths.get(errorFile), String.format("%s%n", input).getBytes(), StandardOpenOption.APPEND);
					   		Files.write(Paths.get(debugFile), msg.getBytes(), StandardOpenOption.APPEND);
					   	}catch (IOException e){
					  		System.out.println("Fail to write the error file" + msg);
					   	}
				     } else{
					    	//If not error, write it to info file
					    	try{
					    		Files.write(Paths.get("info.txt"), msg.getBytes(), StandardOpenOption.APPEND);
					    	}catch (IOException e){
					    		System.out.println("Fail to write the error file" + msg);
					    	}
					    }
				    System.out.println(result);
				}
			}
		}
	}
	
	/**
	 * displayFile - display the content of the 
	 * @param file
	 */
	private static void displayFile(String file){
		try{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			// Read through each line of the file and print it out to the output window.
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println(stringBuffer.toString());
		}catch (IOException e){
			System.out.println("Fail to read the file");
		}
	}
}

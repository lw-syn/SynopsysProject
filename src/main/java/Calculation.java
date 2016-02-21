/* Calculation Class
 * It performs the arithmetic operator of addition, subtraction, multiplication, division
 * 
 * @author Lawrence Wong
 * @since Feb 21, 2016
 * @version 1.0
 *
 */
public class Calculation {
	private int _first;
	private int _second;

	// Default constructor
	Calculation (){
		_first = 0;
		_second = 0;
	}
	
	// Overloaded constructor with two parameters
    Calculation(int first, int second){
    	_first = first;
    	_second = second;
    }
    
    // Perform Addition
    public int add(){
    	return(_first + _second);
    }

    // Perform Subtraction
    public int sub(){
    	return(_first - _second);
    }

    // Perform Multiplication
    public int mult(){
    	return(_first * _second);
    }

    // Perform Division
    // If the denominator is 0, it return Double.NaN
    public double div(){
    	if (_second != 0){
    		return(_first / _second);
    	}else{
    		return Double.NaN;
    	}
    }
}

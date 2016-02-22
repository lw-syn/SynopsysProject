/** Calculation Class
 * It performs the arithmetic operator of addition, subtraction, multiplication, division
 * 
 * @author Lawrence Wong
 * @since Feb 21, 2016
 * @version 1.0
 *
 */
public class Calculation {
	private double _first;
	private double _second;

	/**
	 * Default constructor to set both _frist and _second to zero
	 */
	Calculation (){
		_first = 0.0;
		_second = 0.0;
	}
	
	/**
	 * Overloaded constructor with two parameters to set _frist and _second to pass in parameters
	 * @param first
	 * @param second
	 */
    Calculation(double first, double second){
    	_first = first;
    	_second = second;
    }
    
    /**
     * Perform Addition
     * @return double
     */
    public double add(){
    	return(_first + _second);
    }

    /**
     * Perform Subtraction
     * @return double
     */
    public double sub(){
    	return(_first - _second);
    }

    /**
     *  Perform Multiplication
     * @return double
     */
    public double mult(){
    	return(_first * _second);
    }

    /**
     *  Perform Division. If the denominator is 0, it return Double.NaN
     * @return double
     */
    public double div(){
    	if (_second != 0.0){
    		return(_first / _second);
    	}else{
    		return Double.NaN;
    	}
    }
}

package primitives;
import static primitives.Util.*;
import java.util.Objects;

/**
 * @author sasegal
 *Class to represent a single coordinate
 *Contains one double and basic operations
 */
public class Coordinate {
	public Coordinate(double num) {
		_num = num;
	}
	public Coordinate(Coordinate c) {
		_num =alignZero(c._num);
	}
	private double _num;
	public static Coordinate ZERO = new Coordinate(0.0);
	public double getNum()
	{
		return _num;
	}
	public void setNum(double num)
	{
		_num = num;
	}
	
	/**
	  * to check if two coordinates are equal
	  */

	@Override
	public boolean equals(Object c)
	{
		//self check
		if (this == c)
			return true;
		// null check
	    if (c == null)
	        return false;
	    // type check and cast
  	   if (getClass() != c.getClass())
	        return false;
  	 return usubtract(_num, ((Coordinate)c)._num) == 0.0;
	}
	/**
	 * returns the double as a string
	 */
	@Override
	public String toString()
	{
		return Double.toString(getNum());
	}
	/*
	 * operation to add two coordinates
	 * uses add function from util
	 */
	public Coordinate add(Coordinate c)
	{
		return new Coordinate(Util.uadd(this._num, c._num));
	}
	/*
	 * operation to subtract two coordinates
	 * uses subtract function from Util
	 */
	public Coordinate subtract(Coordinate c)
	{
		return new Coordinate(Util.usubtract(this._num, c._num));
	}
	/*
	 * operation to scale a coordinate
	 * uses scale function from Util
	 */
	public Coordinate scale(double num) {
		return new Coordinate(uscale(_num, num));
	}
	/*
	 *operation to multiply coordinates 
	 */
	public Coordinate multiply(Coordinate other) {
		return new Coordinate(uscale(_num, other._num));
	}
	
	public Coordinate multiply(double other) {
		return new Coordinate(this.getNum()*other);
	}
}

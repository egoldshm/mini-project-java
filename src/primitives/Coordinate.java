package primitives;
import static primitives.Util.*;


/**
 * @author sasegal
 * @info
 *Class to represent a single coordinate
 *Contains one double and basic operations
 *Class to represent a single coordinate
 *Contains one double and basic operations
 */
public class Coordinate {
	
	
	private double _num;
	public static Coordinate ZERO = new Coordinate(0.0);

	
	/** 
	 * A constructor that gets a number
	 * @param num The number to be inserted into the coordinate
	 */
	public Coordinate(double num) {
		_num = num;
	}
	
	/**
	 * copy constructor
	 */
	public Coordinate(Coordinate c) {
		_num =alignZero(c._num);
	}
	
	/**
	 * Returns the num representing the num of the coordinate
	 * @return the num of the coordinate
	 */
	public double getNum()
	{
		return _num;
	}
	
	/**
	 * set the num representing the num of the coordinate
	 * @param num the num of the coordinate
	 */
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
	/*
	 *operation to multiply coordinate with number
	 *Standard scalar product
	 */
	public Coordinate multiply(double other) {
		return new Coordinate(this.getNum()*other);
	}
}

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
	  * 
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
	@Override
	public String toString()
	{
		return Double.toString(getNum());
	}
	public Coordinate add(Coordinate c)
	{
		return new Coordinate(Util.uadd(this._num, c._num));
	}
	public Coordinate subtract(Coordinate c)
	{
		return new Coordinate(Util.usubtract(this._num, c._num));
	}
	public Coordinate scale(double num) {
		return new Coordinate(uscale(_num, num));
	}
	
	public Coordinate multiply(Coordinate other) {
		return new Coordinate(uscale(_num, other._num));
	}
}

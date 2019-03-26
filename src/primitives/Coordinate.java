package primitives;

import java.util.Objects;

public class Coordinate {
	public Coordinate(double num) {
		_num = num;
	}
	public Coordinate(Coordinate c) {
		_num = c._num;
	}
	private double _num;
	public double getNum()
	{
		return _num;
	}
	public void setNum(double num)
	{
		_num = num;
	}
	public Coordinate add(Coordinate c)
	{
		return new Coordinate(_num+c._num);
	}
	public Coordinate subtract(Coordinate c)
	{
		return new Coordinate(_num-c._num);
	}
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
  	   return Objects.equals(_num,((Coordinate)c)._num);
	}
	public String toString()
	{
		return Double.toString(getNum());
	}
}

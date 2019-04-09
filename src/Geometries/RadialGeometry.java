package Geometries;

import java.util.Objects;



/**
 * @author eitan
 *
 */
public abstract class RadialGeometry
{
	protected double _radius;
	public double getRadius()
	{
		return _radius;
	}
	public void setRadius(double r)
	{
		_radius = r;
	}
	@Override
	public boolean equals(Object r)
	{
		//self check
		if (this == r)
			return true;
		// null check
	    if (r == null)
	        return false;
	    // type check and cast
  	   if (getClass() != r.getClass())
	        return false;
  	   return Objects.equals(_radius,((RadialGeometry)r)._radius);
	}
	@Override
	public String toString()
	{
		return Double.toString(_radius);
	}
}

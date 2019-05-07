package Geometries;

import java.util.Objects;



/**
 * @author eitan
 * abstract class for geometry with radius.
 */
public abstract class RadialGeometry extends Geometry
{
	protected double _radius;
	/**
	 * @return the radius of the geomtry
	 */
	public double getRadius()
	{
		return _radius;
	}
	/**
	 * @param r the radius of the geomtry
	 */
	public void setRadius(double r)
	{
		_radius = r;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Double.toString(_radius);
	}
}

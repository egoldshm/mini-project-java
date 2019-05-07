package Geometries;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;



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
	public Map<Geometry, List<Point3D>> findIntersections(Ray r) {
		// TODO fix it
		return null;
	}
}

package Geometries;
import java.util.Objects;

import primitives.Point3D;;;

public class Sphere extends RadialGeometry
{
	private Point3D _center;
	
	public Point3D getCenter()
	{
		return _center;
	}
	
	public void setCenter(Point3D p)
	{
		_center.setX(p.getX());
		_center.setY(p.getX());
		_center.setZ(p.getZ());
	}
	@Override
	public boolean equals(Object s)
	{
		//self check
		if (this == s)
			return true;
		// null check
	    if (s == null)
	        return false;
	    // type check and cast
  	   if (getClass() != s.getClass())
	        return false;
  	   return Objects.equals(_radius,((RadialGeometry)s)._radius)&&_center.equals(((Sphere)s)._center);
	}

	@Override
	public String toString()
	{
		return _center.toString() + " " + Double.toString(_radius);
	}
}

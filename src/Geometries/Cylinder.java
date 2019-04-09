/**
 * 
 */
package Geometries;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;
/**
 * @author sasegal
 * A class represented by Cylinder. realizes Geometry and inherits from RadialGeometry
 */
public class Cylinder extends RadialGeometry implements Geometry
{
	private Point3D _axisPoint;
	private Vector _axisDirection;
	
	/**
	 * @return A point to represent the beginning of a cylinder
	 */
	public Point3D getAxisPoint()
	{
		return _axisPoint;
	}
	/**
	 * @param p A point to represent the beginning of a cylinder
	 */
	public void setAxisPoint(Point3D p)
	{
		_axisPoint.setX(p.getX());
		_axisPoint.setY(p.getY());
		_axisPoint.setZ(p.getZ());
	}
	
	/**
	 * @return Vector direction cylinder
	 */
	public Vector getAxisDirection()
	{
		return _axisDirection;
	}
	/**
	 * @param v Vector direction cylinder
	 */
	public void setAxisDirection(Vector v)
	{
		_axisDirection.setHead(v.getHead());
	}
	
	/* (non-Javadoc)
	 * @see Geometries.RadialGeometry#equals(java.lang.Object)
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
  	   return Objects.equals(_radius,((RadialGeometry)c)._radius)&&_axisPoint.equals(((Cylinder)c)._axisPoint)&&_axisDirection.equals(((Cylinder)c)._axisDirection);
	}
	/* (non-Javadoc)
	 * @see Geometries.RadialGeometry#toString()
	 */
	@Override
	public String toString()
	{
		return _axisPoint.toString() +" "+ _axisDirection.toString()+" "+Double.toString(_radius);
	}
	/* (non-Javadoc)
	 * @see Geometries.Geometry#findIntersections(primitives.Ray)
	 */
	@Override
	public ArrayList<Point3D> findIntersections(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}
}

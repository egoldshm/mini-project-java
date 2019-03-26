/**
 * 
 */
package Geometries;
import primitives.Vector;

import java.util.Objects;

import primitives.Point3D;
/**
 * @author sasegal
 *
 */
public class Cylinder extends RadialGeometry
{
	private Point3D _axisPoint;
	private Vector _axisDirection;
	
	public Point3D getAxisPoint()
	{
		return _axisPoint;
	}
	public void setAxisPoint(Point3D p)
	{
		_axisPoint.setX(p.getX());
		_axisPoint.setY(p.getY());
		_axisPoint.setZ(p.getZ());
	}
	
	public Vector getAxisDirection()
	{
		return _axisDirection;
	}
	public void setAxisDirection(Vector v)
	{
		_axisDirection.setHead(v.getHead());
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
  	   return Objects.equals(_radius,((RadialGeometry)c)._radius)&&_axisPoint.equals(((Cylinder)c)._axisPoint)&&_axisDirection.equals(((Cylinder)c)._axisDirection);
	}
	public String toString()
	{
		return _axisPoint.toString() +" "+ _axisDirection.toString()+" "+Double.toString(_radius);
	}
}

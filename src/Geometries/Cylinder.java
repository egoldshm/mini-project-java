/**
 * 
 */
package Geometries;
import primitives.Vector;
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
	public String toString()
	{
		return _axisPoint.toString() +" "+ _axisDirection.toString()+" "+Double.toString(_radius);
	}
}

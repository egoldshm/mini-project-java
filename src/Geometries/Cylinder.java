/**
 * 
 */
package Geometries;
import primitives.Material;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;
/**
 * @author sasegal
 * A class to represent Cylinder. realizes Geometry and inherits from RadialGeometry
 */
public class Cylinder extends RadialGeometry
{
	private Point3D _axisPoint;
	private Vector _axisDirection;
	
	// ***************** Constructors ********************** // 
	/**
	 * @param _axisPoint
	 * @param _axisDirection
	 */
	public Cylinder(Point3D _axisPoint, Vector _axisDirection) {
		this._axisPoint = _axisPoint;
		this._axisDirection = _axisDirection;
	}
	
	/**
	 * default ctor
	 */
	public Cylinder() {
		_axisPoint = new Point3D();
		_axisDirection = new Vector();
	}
	
	/**
	 * copy ctor
	 */
	public Cylinder(Cylinder cylinder) {
		this._axisPoint = cylinder._axisPoint;
		this._axisDirection = cylinder._axisDirection;
	}

	public Cylinder(Color emmission, Material material, double _radius, Point3D _axisPoint, Vector _axisDirection) {
		super(emmission, material, _radius);
		this._axisPoint = _axisPoint;
		this._axisDirection = _axisDirection;
	}

	// ***************** Getters/Setters ********************** //

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

	// ***************** Operations ******************** // 
	/* (non-Javadoc)
	 * @see Geometries.Geometry#findIntersections(primitives.Ray)
	 */
	@Override
	public List<Point3D> findIntersections(Ray r) {
		// TODO fix
		return null;
	}
	
	@Override
	public Vector getNormal(Point3D point) {
		// TODO fix
		return null;
	}
	
	// ***************** Admin ********************** //
	
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
}

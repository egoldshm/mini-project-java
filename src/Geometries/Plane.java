/**
 * 
 */
package Geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * @author egoldshm and sarieldov
 *
 */
public class Plane implements Geometry{

	/**
	 * 
	 */
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	public Plane() {
		
	}
	
	public Plane(Plane temp) 
	{
		_p1 = temp._p1;
		_p2 = temp._p2;
		_p3 = temp._p3;
	}
	
	public Point3D getP1()
	{
		return _p1;
	}
	
	public void setP1(Point3D newPoint)
	{
		_p1 = newPoint;
	}
	

	public Point3D getP2()
	{
		return _p2;
	}
	
	public void setP2(Point3D newPoint)
	{
		_p2 = newPoint;
	}
	

	public Point3D getP3()
	{
		return _p3;
	}
	
	public void setP3(Point3D newPoint)
	{
		_p3 = newPoint;
	}
	@Override
	public boolean equals(Object p)
	{
		//self check
		if (this == p)
			return true;
		// null check
		if (p == null)
			return false;
		// type check and cast
		if (getClass() != p.getClass())
			return false;
		return _p1.equals(((Plane)p)._p1)&&_p2.equals(((Plane)p)._p2)&&_p3.equals(((Plane)p)._p3);
	}

	@Override
	public String toString()
	{
		return _p1.toString()+" "+_p2.toString()+" "+_p3.toString();
	}
	
	//TODO:Prepare this function
	public ArrayList<Point3D> findIntersections(Ray ray);

	{
		
	}

	
}

/**
 * 
 */
package Geometries;
import java.util.ArrayList;

import primitives.*;
/**
 * @author egoldshm and sarieldov
 *
 */
public class Triangle implements Geometry{

	/**
	 * 
	 */
	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	public Triangle() {
		
	}
	
	public Triangle(Triangle temp) 
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
	public boolean equals(Object t)
	{
		//self check
		if (this == t)
			return true;
		// null check
	    if (t == null)
	        return false;
	    // type check and cast
  	   if (getClass() != t.getClass())
	        return false;
  	   return _p1.equals(((Triangle)t)._p1)&&_p2.equals(((Triangle)t)._p2)&&_p3.equals(((Triangle)t)._p3);
	}

	@Override
	public String toString()
	{
		return _p1.toString()+" "+_p2.toString()+" "+_p3.toString();
	}

	@Override
	public ArrayList<Point3D> findIntersections(Ray r) {
		ArrayList<Point3D> returnList = new ArrayList<Point3D>();
		//to check if there is a point of intersection on the plane the triangle is on
		returnList.addAll(new Plane(_p1.subtract(_p2).CrossProductVector(_p1.subtract(_p3)), _p1).findIntersections(r));
		//if there isn't, then there is definitely no intersection in the triangle
		if(returnList.isEmpty())
		{
			return returnList;
		}
		//if there is a point of intersection, we need to see if it is also on the triangle
		Vector N1 = (this._p2.subtract(r.getPOO()).CrossProductVector(this._p3.subtract(r.getPOO())).normalizationOfVector());
		Vector N2 = (this._p1.subtract(r.getPOO()).CrossProductVector(this._p3.subtract(r.getPOO())).normalizationOfVector());
		Vector N3 = (this._p1.subtract(r.getPOO()).CrossProductVector(this._p2.subtract(r.getPOO())).normalizationOfVector());
		Vector v = returnList.get(0).subtract(r.getPOO());
		//the point is in the same position relative to the "triangles" built by the vectors N1, N2, N3 (three triangles - the sides of a tetrahedron with base "this" and head of P0), meaning it must be on the triangle 
		if(Math.signum(v.scalarMultiplication(N1)) == Math.signum(v.scalarMultiplication(N2)) && Math.signum(v.scalarMultiplication(N2)) == Math.signum(v.scalarMultiplication(N3)))
		{
			return returnList;
		}
		//no intersection points
		else
		{
			returnList.clear();
			return returnList;
		}
	}
}

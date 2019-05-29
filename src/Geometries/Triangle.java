/**
 * 
 */
package Geometries;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;
/**
 * @author egoldshm and sarieldov
 * A class to represent Triangle. realizes Geometry.
 */
public class Triangle extends Geometry{

	Point3D _p1;
	Point3D _p2;
	Point3D _p3;
	
	// ***************** Constructors ********************** // 
	
	/**
	 * default constructor
	 */
	public Triangle() {
		super();
		_p1 = new Point3D();
		_p2 = new Point3D();
		_p3 = new Point3D();
	}

	/**
	 * constructor
	 * @param p1 first point of the triangle
	 * @param p2 second point of the triangle
	 * @param p3 third point of the triangle
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super();
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}

	/**
	 * copy constructor
	 */
	public Triangle(Triangle temp) 
	{
		super(temp);
		_p1 = temp._p1;
		_p2 = temp._p2;
		_p3 = temp._p3;
	}

	/**
	 * constructor
	 * @param emmission of the triangle
	 * @param material of the triangle
	 * @param p1 first point of the triangle
	 * @param p2 second point of the triangle
	 * @param p3 third point of the triangle
	 */
	public Triangle(Color emmission, Material material, Point3D _p1, Point3D _p2, Point3D _p3) {
		super(emmission, material);
		this._p1 = _p1;
		this._p2 = _p2;
		this._p3 = _p3;
	}

	// ***************** Getters/Setters ********************** //

	
	/**
	 * @return One point of triangle
	 */
	public Point3D getP1()
	{
		return _p1;
	}
	
	/**
	 * @param newPoint One point of triangle
	 */
	public void setP1(Point3D newPoint)
	{
		_p1 = newPoint;
	}
	
	/**
	 * @return A second point of the triangle
	 */
	public Point3D getP2()
	{
		return _p2;
	}
	
	/**
	 * @param newPoint A second point of the triangle
	 */
	public void setP2(Point3D newPoint)
	{
		_p2 = newPoint;
	}

	/**
	 * @return Third point of triangle
	 */
	public Point3D getP3()
	{
		return _p3;
	}
	
	/**
	 * @param newPoint Third point of triangle
	 */
	public void setP3(Point3D newPoint)
	{
		_p3 = newPoint;
	}
	
	// ***************** Operations ******************** // 
	
	/* (non-Javadoc)
	 * @see Geometries.Geometry#findIntersections(primitives.Ray)
	 */
	@Override
	public List<Point3D> findIntersections(Ray r) {
		
		List<Point3D> returnList = new ArrayList<Point3D>();
		//to check if there is a point of intersection on the plane the triangle is on
		
		//TODO: check -> auto fixed
		returnList.addAll((Collection<? extends Point3D>) new Plane(_p1.subtract(_p2).CrossProductVector(_p1.subtract(_p3)), _p1).findIntersections(r));
		//if there isn't, then there is definitely no intersection in the triangle
		if(returnList.isEmpty())
		{
			return returnList;

		}
		//if there is a point of intersection, we need to see if it is also on the triangle
		Vector v1 = this._p1.subtract(r.getPOO());
		Vector v2 = this._p2.subtract(r.getPOO());
		Vector v3 = this._p3.subtract(r.getPOO());
		Vector N1 = v2.CrossProductVector(v1).normalizationOfVector();
		Vector N2 = v3.CrossProductVector(v2).normalizationOfVector();
		Vector N3 = v1.CrossProductVector(v3).normalizationOfVector();
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

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		Vector v1 = this._p2.subtract(this._p1);
		Vector v2 = this._p3.subtract(this._p1);
		return v1.CrossProductVector(v2).normalizationOfVector();
	}
	
	// ***************** Admin ********************** //
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return _p1.toString()+" "+_p2.toString()+" "+_p3.toString();
	}

}

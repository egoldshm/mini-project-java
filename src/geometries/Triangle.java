/**
 * 
 */
package geometries;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * @author egoldshm and sarieldov
 * A class to represent Triangle. realizes Geometry.
 */
public class Triangle extends Geometry implements FlatGeometry{

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
	 * @see geometries.Intersectable#findIntersections(primitives.Ray)
	 */
	public List<Point3D> findIntersections(Ray ray) {

	    List<Point3D> intersections = new ArrayList<Point3D>();

	    Plane plane = new Plane(this.getNormal(_p1),this._p1);
	    		intersections = plane.findIntersections(ray);
	    if (intersections.isEmpty())
	        return intersections;

	    Point3D p0 = ray.getPOO();

	    Vector v1 = _p1.subtract(p0).normalizationOfVector();
	    Vector v2 = _p2.subtract(p0).normalizationOfVector();
	    Vector v3 = _p3.subtract(p0).normalizationOfVector();

	    Vector n1 = new Vector(v1.CrossProductVector(v2));
	    Vector n2 = new Vector(v2.CrossProductVector(v3));
	    Vector n3 = new Vector(v3.CrossProductVector(v1));

	    Point3D p = new Point3D(intersections.get(0));

	    if (!((Math.signum(p.subtract(p0).scalarMultiplication(n1)) ==
	            Math.signum(p.subtract(p0).scalarMultiplication(n2)) &&
	            Math.signum(p.subtract(p0).scalarMultiplication(n2)) ==
	                    Math.signum(p.subtract(p0).scalarMultiplication(n3)))))
	        intersections.clear();

	    else {
	        if (intersections.get(0).subtract(ray.getPOO()).scalarMultiplication(ray.getDirection()) < 0)
	            intersections.clear();
	    }

	    return intersections;
	}

	/* (non-Javadoc)
	 * @see geometries.Geometry#getNormal(primitives.Point3D)
	 */
	@Override
	public Vector getNormal(Point3D point) {
		Vector v1 = this._p2.subtract(this._p1);
		Vector v2 = this._p3.subtract(this._p1);
		return (v1.CrossProductVector(v2).normalizationOfVector()).scalarMultiplication(-1);
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

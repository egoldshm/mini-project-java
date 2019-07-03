/** *
 */
package geometries;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;
/**
 * @author egoldshm and sarieldov
 * @info
 * 		 A class represented by plane. realizes Geometry.
 */
public class Plane extends Geometry implements FlatGeometry{

	Vector _N;
	Point3D _Q;
	
	// ***************** Constructors ********************** //
	
	/**
	 * Default constructor
	 */
	public Plane() {
		
	}
	
	/**
	 * constructor with params for plane
	 * @param v Vertical vector to surface
	 * @param p point on the plane
	 */
	public Plane(Vector v, Point3D p)
	{
		_N = v;
		_Q = p;
	}
	
	/**
	 * Copy constructor
	 */
	public Plane(Plane temp) 
	{
		_N = temp._N;
		_Q = temp._Q;
	}

	/**
	 * @param emmission color of the plane
	 * @param material of the plane
	 * @param _N Vertical vector to surface
	 * @param _Q point on the plane
	 */
	public Plane(Color emmission, Material material, Vector _N, Point3D _Q) {
		super(emmission, material);
		this._N = _N;
		this._Q = _Q;
	}

	// ***************** Getters/Setters ********************** //
	
	/**
	 * @return Vertical vector to surface
	 */
	public Vector getN()
	{
		return _N;
	}
	
	/**
	 * @param N Vertical vector to surface
	 */
	public void setN(Vector N)
	{
		_N = N;
	}	

	/**
	 * @return point on the plane
	 */
	public Point3D getQ()
	{
		return _Q;
	}
	
	/**
	 * @param newPoint point on the plane
	 */
	public void setQ(Point3D newPoint)
	{
		_Q = newPoint;
	}
	
	// ***************** Operations ******************** // 

	/* (non-Javadoc)
	 * @see Geometries.Geometry#findIntersections(primitives.Ray)
	 */
	public List<Point3D> findIntersections(Ray r)
	{
		 List<Point3D> L=new ArrayList<Point3D>(); // the LIst of points that intersect the plane
	        Point3D p0=new Point3D(r.getPOO()); // the point that the ray comes out from
	        Point3D  q0=new Point3D(_Q); // a point on the plane
	        Vector N=new Vector(this._N); //the normal vector of the plane
	        N = N.scalarMultiplication(-1);
	        Vector V=new Vector(r.getDirection()); // the vector that describes the deriction of the ray(without th point of the ray, just the vector)
	        Vector v= q0.subtract(p0); // the vector that describes the deriction between p0 and q0
	        //the algorithem is checking if the ray intersect the plane somewhere
	        double t1=N.scalarMultiplication(v);//dot product between N and v
	        N = N.scalarMultiplication(-1);
	        double t2=N.scalarMultiplication(V);//dot product between N and v to t2
	        double t=t1/t2;//t=t1/t2
	        if(t>=0)
	        {
	            V = V.scalarMultiplication(t);
	            p0.add(V);
	            L.add(p0);
	            return L;
	        }
	        return L;
	    /*
		List<Point3D> returnList = new ArrayList<Point3D>();
		//if there is an intersection, it will be t * r.vector away from r.point
		double t = -1 * (_N.scalarMultiplication(r.getPOO().subtract(_Q)))/(_N.scalarMultiplication(r.getDirection()));
		//this scalar multiplication will return zero if the point P0 + t*v is on the plane (90 degree angle)
		if(Util.isZero(_N.scalarMultiplication(r.getPOO().add(r.getDirection().scalarMultiplication(t)).subtract(_Q))))	
		{
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t)));
		}
		
		//intersections.put(this, returnList);
		return returnList;*/
		
	}

	/* (non-Javadoc)
	 * @see Geometries.Geometry#getNormal(primitives.Point3D)
	 */
	@Override
	public Vector getNormal(Point3D point) { 
		return _N.normalizationOfVector();
	}
	
	// ***************** Admin ********************** //
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		return _N.equals(((Plane)p)._N)&&_Q.equals(((Plane)p)._Q);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return _N.toString()+" "+_Q.toString();
	}


	
}

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
public class Plane implements Geometry{

	/**
	 * 
	 */
	Vector _N;
	Point3D _Q;
	
	public Plane() {
		
	}
	
	public Plane(Vector v, Point3D p)
	{
		_N = v;
		_Q = p;
	}
	
	public Plane(Plane temp) 
	{
		_N = temp._N;
		_Q = temp._Q;
	}
	
	public Vector getN()
	{
		return _N;
	}
	
	public void setN(Vector N)
	{
		_N = N;
	}
	

	public Point3D getQ()
	{
		return _Q;
	}
	
	public void setQ(Point3D newPoint)
	{
		_Q = newPoint;
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
		return _N.equals(((Plane)p)._N)&&_Q.equals(((Plane)p)._Q);
	}

	@Override
	public String toString()
	{
		return _N.toString()+" "+_Q.toString();
	}
	
	@Override
	//TODO:Prepare this function
	public ArrayList<Point3D> findIntersections(Ray r)
	{
		ArrayList<Point3D> returnList = new ArrayList<Point3D>();
		double t = -1 * (_N.scalarMultiplication(r.getPOO().subtract(_Q)))/(_N.scalarMultiplication(r.getDirection()));
		if(_N.scalarMultiplication(r.getPOO().add(r.getDirection().scalarMultiplication(t)).subtract(_Q))==0)			
		{
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t)));
		}
		return returnList;
		
	}

	
}

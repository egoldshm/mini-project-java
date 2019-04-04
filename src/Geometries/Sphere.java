package Geometries;
import java.util.ArrayList;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

public class Sphere extends RadialGeometry implements Geometry
{
	private Point3D _center;
	
	public Point3D getCenter()
	{
		return _center;
	}
	
	public void setCenter(Point3D p)
	{
		_center.setX(p.getX());
		_center.setY(p.getX());
		_center.setZ(p.getZ());
	}
	@Override
	public boolean equals(Object s)
	{
		//self check
		if (this == s)
			return true;
		// null check
	    if (s == null)
	        return false;
	    // type check and cast
  	   if (getClass() != s.getClass())
	        return false;
  	   return Objects.equals(_radius,((RadialGeometry)s)._radius)&&_center.equals(((Sphere)s)._center);
	}

	@Override
	public String toString()
	{
		return _center.toString() + " " + Double.toString(_radius);
	}

	@Override
	public ArrayList<Point3D> findIntersections(Ray r) {
		Vector L = new Vector(_center.subtract(r.getPOO()));
		double tm = L.scalarMultiplication(r.getDirection());
		double d = Math.sqrt(_center.squaredDistance(r.getPOO()) - tm * tm);
		ArrayList<Point3D> returnList = new ArrayList<Point3D>();
		if(d > this._radius)
		{
			return returnList;
		}
		if(d==this._radius)
		{
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(tm)));
			return returnList;
		}
		double th = Math.sqrt(this._radius * this._radius - d * d );
		double t1 = tm - th, t2= tm + th;
		if(t1 > 0)
		{
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t1)));
		}
		if(t2 > 0)
		{
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t2)));
		}
		return returnList;
	}
}

package Geometries;
import primitives.Point3D;;;

public class Sphere extends RadialGeometry
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
	public String toString()
	{
		return _center.toString() + " " + Double.toString(_radius);
	}
}

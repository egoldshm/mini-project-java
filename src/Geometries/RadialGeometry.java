package Geometries;

public abstract class RadialGeometry
{
	private double _radius;
	public double getRadius()
	{
		return _radius;
	}
	public void setRadius(double r)
	{
		_radius = r;
	}
	@Override
	public String toString()
	{
		return Double.toString(_radius);
	}
}

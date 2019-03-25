package primitives;
/**
 * class for 3D point
 * @author egoldshm and sarieldov
 */
public class Point3D extends Point2D {
	
	public Point3D()
	{
		_z = new Coordinate(0);
	}
	
	private Coordinate _z;
	public Coordinate getZ()
	{
		return _z;
	}
	
	public void setZ(Coordinate z)
	{
		_z = z;
	}
	
	@Override
	public String toString() 
	{
		return super.toString() + ", " +_z.toString();
	}
}

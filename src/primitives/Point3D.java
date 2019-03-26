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
	public Point3D(Coordinate x, Coordinate y, Coordinate z)
	{
		_x.setNum(x.getNum());
		_y.setNum(y.getNum());
		_z.setNum(z.getNum());
	}
	public Point3D(Point3D p)
	{
		_x=p._x;
		_y=p._y;
		_z=p._z;
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
  	   return _x.equals((((Point3D)p)._x))&&_y.equals((((Point3D)p)._y))&&_z.equals((((Point3D)p)._z));
	}
	public String toString() 
	{
		return super.toString() + ", " +_z.toString();
	}
}

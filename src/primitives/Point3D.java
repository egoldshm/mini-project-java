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
	public Vector subtract(Point3D p)
	{
		return new Vector(new Point3D(_x.subtract(p._x), _y.subtract(_y),_z.subtract(p._z)));
	}
	public Point3D add(Vector v)
	{
		return new Point3D(_x.add(v.getHead()._x), _y.add(v.getHead()._y), _z.add(v.getHead()._z));
	}
	public double squaredDistance(Point3D p)
	{
		Point3D ptemp = this.subtract(p).getHead();
		return ptemp._x.getNum()*ptemp._x.getNum()+ptemp._y.getNum()*ptemp._y.getNum()+ptemp._z.getNum()*ptemp._z.getNum();
	}
	public double distance(Point3D p)
	{
		return Math.sqrt(this.squaredDistance(p));
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

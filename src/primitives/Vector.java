package primitives;

public class Vector
{
	private Point3D _head;
	public Vector(Point3D p)
	{
		_head=p;
	}
	public Vector(Vector v)
	{
		_head = v._head;
	}
	public Point3D getHead()
	{
		return _head;
	}
	public void setHead(Point3D p)
	{
		_head.setX(p.getX());
		_head.setY(p.getY());
		_head.setZ(p.getZ());
	}
	
	@Override
	public boolean equals(Object v)
	{
		//self check
		if (this == v)
			return true;
		// null check
	    if (v == null)
	        return false;
	    // type check and cast
  	   if (getClass() != v.getClass())
	        return false;
  	   return _head.equals(((Vector)v)._head);
	}
	public String toString()
	{
		return _head.toString();
	}
}

package primitives;

public class Ray 
{
	private Point3D _POO;
	private Vector _direction;
	
	public Ray(Point3D POO, Vector direction)
	{
		_POO=POO;
		_direction=direction;
	}
	public Ray(Ray r)
	{
		_POO = r._POO;
		_direction=r._direction;
	}
	public Point3D getPOO()
	{
		return _POO;
	}
	public void setPOO(Point3D p)
	{
		_POO.setX(p.getX());
		_POO.setY(p.getY());
		_POO.setZ(p.getZ());
	}
	public Vector getDirection()
	{
		return _direction;
	}
	public void setDirection(Vector v)
	{
		_direction.setHead(v.getHead());
	}
	 @Override
	 public boolean equals(Object r)
		{
			//self check
			if (this == r)
				return true;
			// null check
		    if (r == null)
		        return false;
		    // type check and cast
	  	   if (getClass() != r.getClass())
		        return false;
	  	   return _POO.equals(((Ray)r)._POO)&&_direction.equals(((Ray)r)._direction);
		}
	 public String toString()
	 {
		 return _POO.toString()+" "+_direction.toString();
	 }
}

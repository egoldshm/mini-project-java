package primitives;

public class Ray 
{
	private Point3D _POO;
	private Vector _direction;
	
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
	 public String toString()
	 {
		 return _POO.toString()+" "+_direction.toString();
	 }
}

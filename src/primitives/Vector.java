package primitives;

public class Vector
{
	private Point3D _head;
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
	public String toString()
	{
		return _head.toString();
	}
}

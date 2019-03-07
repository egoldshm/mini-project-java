package primitives;
public class Point3D extends Point2D {
	
	public Point3D()
	{
		_z = new Coordinate(0);
	}
	
	@Override
	public String toString() {
		return "("+ getX() + "," + getY() + "," + getZ()+")";
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
}

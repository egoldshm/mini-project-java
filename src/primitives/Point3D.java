package primitives;
/**
 * class for 3D point
 * @author egoldshm and sarieldv
 */
public class Point3D extends Point2D {
	
	/**
	 * First axis for 3D space
	 */
	public static Point3D ZERO = new Point3D(Coordinate.ZERO,Coordinate.ZERO,Coordinate.ZERO);

	/**
	 * Default constructor
	 */
	public Point3D()
	{
		_z = new Coordinate(0);
	}
	
	/**
	 * Constructor with parameters
	 * 
	 * @param x For the X axis
	 * @param y For the Y axis
	 * @param z For the Z axis
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) 
	{
		super.setX(x);
		super.setY(y);
		this.setZ(new Coordinate(z.getNum()));
	}
	/**
	 * Constructor with parameters
	 * 
	 * @param x For the X axis
	 * @param y For the Y axis
	 * @param z For the Z axis
	 */
	public Point3D(double x, double y, double z)
	{
		this.setX(new Coordinate(x));
		this.setY(new Coordinate(y));
		this.setZ(new Coordinate(z));
	}
	/**
	 * copy constructor
	 */
	public Point3D(Point3D p)
	{
		_x=p._x;
		_y=p._y;
		_z=p._z;
	}
	
	private Coordinate _z;
	
	/**
	 * Returns the Z-axis for the point
	 * @return Z z-axis
	 */
	public Coordinate getZ()
	{
		return _z;
	}
	
	/**
	 * Sets the Z-axis for the point
	 * @param z z-axis
	 */
	public void setZ(Coordinate z)
	{
		_z = z;
	}
	
	/**
	 * 
	 * Subtracts between two points and returns the vector between them
	 * @param p The second point
	 * @return Vector between them
	 */
	public Vector subtract(Point3D p)
	{
		return new Vector(new Point3D(_x.subtract(p._x), _y.subtract(p._y),_z.subtract(p._z)));
	}
	
	
	/**
	 * Adds a vector to a point and returns the result as a point
	 * @param v vector to add
	 * @return point of the result
	 */
	public Point3D add(Vector v)
	{
		return new Point3D(_x.add(v.getHead()._x), _y.add(v.getHead()._y), _z.add(v.getHead()._z));
	}
	
	/**
	 * 
	 * Point Multiplier in Scaler Normal multiplication (any point in the same number)
	 * @param num the number to multiply
	 * @return the result
	 */
	public Point3D multiply(double num)
	{
		return new Point3D(this._x.multiply(num),this._y.multiply(num),this._z.multiply(num));
	}
	
	/**
	 * Finds a distance between two points by the Pythagoras theorem and returns the square
	 * @param p the second point
	 * @return distance square
	 */
	public double squaredDistance(Point3D p)
	{
		Point3D ptemp = new Point3D(p._x.subtract(_x), p._y.subtract(_y), p._z.subtract(_z));
		return (ptemp._x.getNum()*ptemp._x.getNum())+(ptemp._y.getNum()*ptemp._y.getNum())+(ptemp._z.getNum()*ptemp._z.getNum());
	}
	
	/**
	 * Finds a distance between two points by the Pythagoras theorem and returns the result
	 * @param p the second point
	 * @return distance
	 */
	public double distance(Point3D p)
	{
		return Math.sqrt(this.squaredDistance(p));
	}
	
	/**
	  * to check if two Point3D are equal
	  */
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
  	   return getX().equals((((Point3D)p).getX()))&& getY().equals((((Point3D)p).getY()))&&getZ().equals((((Point3D)p).getZ()));
	}
	
	/**
	 * returns the Point3D as a string
	 */
	@Override
	public String toString() 
	{
		return super.toString() + ", " +_z.toString();
	
	}
}

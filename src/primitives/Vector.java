package primitives;

/**
 * @author egoldshm
 * @info
 * Vector representation class
 */
public class Vector
{
	
	private Point3D _head;
	
	// ***************** Constructors ********************** // 

	/**
	 * Constructor with parameters
	 */
	public Vector(Point3D p)
	{
		/*if(p.equals(Point3D.ZERO))
		{
			throw new ArithmeticException("A vector cannot have the value of (0, 0, 0).");
		}*/
		_head=new Point3D(p);
	}
	
	/**
	 * Constructor with double parameters
	 */
	public Vector(double x, double y, double z)
	{
		/*if(x==0 && y==0 && z==0)
		{
			throw new ArithmeticException("A vector cannot have the value of (0, 0, 0).");
		}*/
		_head = new Point3D(new Coordinate(x), new Coordinate(y), new Coordinate(z));
	}
	
	/**
	 * default ctor
	 */
	public Vector()
	{
		this._head = new Point3D(1, 1, 1);
	}
	
	/**
	 * copy parameters
	 */
	public Vector(Vector v)
	{
		/*if(v._head.equals(Point3D.ZERO))
		{
			throw new ArithmeticException("A vector cannot have the value of (0, 0, 0).");
		}*/
		_head = new Point3D(v._head);
	}
	
	// ***************** Getters/Setters ********************** //
	/**
	 * @return The point of the vector end (beginning at the beginning of the contractions)
	 */
	public Point3D getHead()
	{
		return _head;
	}
	
	/**
	 * @param p The point of the vector end (beginning at the beginning of the contractions)
	 */
	public void setHead(Point3D p)
	{
		_head.setX(p.getX());
		_head.setY(p.getY());
		_head.setZ(p.getZ());
	}
	
	// ***************** Operations ******************** // 

	/**
	 * Connects two vectors by connecting their two points
	 */
	public Vector addVector(Vector v)
	{
		return new Vector(new Point3D(v.getHead().getX().add(this._head.getX()), v.getHead().getY().add(this._head.getY()),v.getHead().getZ().add(this._head.getZ())));
	}
	
	/**
	 * subtract 2 Vectors
	 * 
	 * @param v the vector to subtract
	 * @return new vector of the result
	 */
	public Vector subtractVector(Vector v)
	{
		return new Vector(new Point3D(this.getHead().getX().subtract(v._head.getX()), this.getHead().getY().subtract(v._head.getY()),this.getHead().getZ().subtract(v._head.getZ())));
	}
	
	/**
	 * Multipy 2 Vectors scalar Multiplication
	 * 
	 * @param v the vector to Multiply
	 * @return double of the result
	 */
	public double scalarMultiplication(Vector v)
	{
		Coordinate xMultiply = v.getHead().getX().multiply(this._head.getX());
		Coordinate yMultiply = v.getHead().getY().multiply(this._head.getY());
		Coordinate zMultiply = v.getHead().getZ().multiply(this._head.getZ());
		return (xMultiply.add(yMultiply).add(zMultiply)).getNum();
	}
	
	/**
	 * Multiply Vector with scalar, scalar Multiplication
	 * 
	 * @param number the scalar to multiply
	 * @return new vector of the result
	 */
	public Vector scalarMultiplication(double number)
	{
		return new Vector(new Point3D(this._head.getX().scale(number), this._head.getY().scale(number),this._head.getZ().scale(number)));
	}
	
	/**
	 * Multiply 2 Vectors vector product
	 * 
	 * @param v the vector to Multiply
	 * @return new vector of the result
	 */
	public Vector CrossProductVector(Vector v)
	{
		return new Vector(new Point3D(
				(this.getHead().getY().multiply(v.getHead().getZ())).subtract(this.getHead().getZ().multiply(v.getHead().getY())),
				(this.getHead().getZ().multiply(v.getHead().getX())).subtract(this.getHead().getX().multiply(v.getHead().getZ())),
				(this.getHead().getX().multiply(v.getHead().getY())).subtract(this.getHead().getY().multiply(v.getHead().getX()))));
	}
	
	/**
	 * length of vector (distance from Zero point)
	 * @return the length of the vector
	 */
	public double length()
	{
		return this._head.distance(Point3D.ZERO);
	}
	
	/**
	 * normalization of the vector, dividing the vector by its length
	 * 
	 * @return the new normalization Vector
	 */
	public Vector normalizationOfVector()
	{
		return new Vector(this.getHead().multiply(1/this.length()));
				
	}
	
	// ***************** Admin ******************** // 

	/**
	  * to check if two vectors are equal
	  */
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
	
	/**
	* returns the ray as a string
	*/
	@Override
	public String toString()
	{
		return _head.toString();
	}
	
}

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
	 * @param v the vector to Multiplicate
	 * @return new vector of the result
	 */
	public Vector scalarMultiplication(Vector v)
	{
		return new Vector(new Point3D(v.getHead().getX().multiply(this._head.getX()), v.getHead().getY().multiply(this._head.getY()),v.getHead().getZ().multiply(this._head.getZ())));
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
}

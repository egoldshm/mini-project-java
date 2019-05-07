package primitives;

/**
 * @author sasegal
 * @info
 * Class to represent a ray (a vector with a source)
 */
public class Ray 
{
	private Point3D _POO;
	private Vector _direction;
	
	// ***************** Constructors ********************** // 
	/**
	 * Constructor with parameters
	 */
	public Ray(Point3D POO, Vector direction)
	{
		_POO=POO;
		_direction=direction;
	}
	
	/**
	 * copy parameters
	 */
	public Ray(Ray r)
	{
		_POO = r._POO;
		_direction=r._direction;
	}
	
	// ***************** Getters/Setters ********************** //
	
	/**
	 * @Return the point that symbolizes the starting point of the ray
	 */
	public Point3D getPOO()
	{
		return _POO;
	}
	
	/**
	 * @param p point that symbolizes the starting point of the ray
	 */
	public void setPOO(Point3D p)
	{
		_POO.setX(p.getX());
		_POO.setY(p.getY());
		_POO.setZ(p.getZ());
	}
	
	/**
	 * @return The direction vector of the ray
	 */
	public Vector getDirection()
	{
		return _direction.normalizationOfVector();
	}
	
	/**
	 * @param v The direction vector of the ray
	 */
	public void setDirection(Vector v)
	{
		_direction.setHead(v.getHead());
		
	}
	
	
	 
	// ***************** Admin ********************** //
	/**
	* returns the ray as a string
	*/
	 @Override
	 public String toString()
	 {
		 return _POO.toString()+" "+_direction.toString();
	 }
	 
	 /**
	  * to check if two Rays are equal
	  */
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
}

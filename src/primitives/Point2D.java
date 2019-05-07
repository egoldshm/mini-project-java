package primitives;


/**
 * @author sasegal
 * @info
 *Class to represent a 2 dimensional point
 */
public class Point2D {
	
		protected Coordinate _x;
		protected Coordinate _y;
		// ***************** Constructors ********************** // 
		/**
		 * default ctor
		 */
		public Point2D()
		{
			_x = new Coordinate(0);
			_y = new Coordinate(0);

		}

		/**
		 * constractor with 2 params
		 */
		public Point2D(Coordinate x, Coordinate y)
		{
			_x = x;
			_y = y;

		}
		
		// ***************** Getters/Setters ********************** //
		/**
		 * Returns the X-axis for the point
		 * @return X x-axis
		 */
		public Coordinate getX()
		{
			return _x;
		}
	
		/**
		 * Sets the X-axis for the point
		 * @param x X-axis
		 */
		public void setX(Coordinate x)
		{
			_x = x;
		}
		/**
		 * Returns the Y-axis for the point
		 * @return Y Y-axis
		 */
		public Coordinate getY()
		{
			return _y;
		}
		
		/**
		 * Sets the Y-axis for the point
		 * @param x Y-axis
		 */
		public void setY(Coordinate y)
		{
			_y = y;
		}
		
		
		// ***************** Admin ********************** //
		/**
		  * to check if two Point2D are equal
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
	  	   return _x.equals((((Point2D)p)._x))&&_y.equals((((Point2D)p)._y));
		}
		
		/**
		 * returns the Point2D as a string
		 */
		@Override
		public String toString()
		{
			return _x.toString() + ", " + _y.toString();
		}
	}


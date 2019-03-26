package primitives;

import java.util.Objects;

public class Point2D {
		protected Coordinate _x;
		protected Coordinate _y;
		public Coordinate getX()
		{
			return _x;
		}
		public void setX(Coordinate x)
		{
			_x = x;
		}
		public Coordinate getY()
		{
			return _y;
		}
		public void setY(Coordinate y)
		{
			_y = y;
		}
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
		public String toString()
		{
			return _x.toString() + ", " + _y.toString();
		}
	}


package Geometries;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;



/**
 * @author eitan
 * abstract class for geometry with radius.
 */
public abstract class RadialGeometry extends Geometry
{
	protected double _radius;
	
	// ***************** Constructors ********************** // 
	
	public RadialGeometry() {
		super();
		this._radius = 0;
	}

	public RadialGeometry(RadialGeometry r) {
		this._radius = r.getRadius();
	}
	
	public RadialGeometry(double _radius) {
		this._radius = _radius;
	}

	public RadialGeometry(Color emmission, Material material, double _radius) {
		super(emmission, material);
		this._radius = _radius;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * @return the radius of the geomtry
	 */
	public double getRadius()
	{
		return _radius;
	}
	
	/**
	 * @param r the radius of the geomtry
	 */
	public void setRadius(double r)
	{
		_radius = r;
	}
	
	
	// ***************** Admin ********************** //
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
  	   return Objects.equals(_radius,((RadialGeometry)r)._radius);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Double.toString(_radius);
	}
}

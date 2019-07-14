package geometries;

import java.awt.Color;
import java.util.Objects;

import primitives.Material;



/**
 * @author eitan
 * @info
 * 		abstract class for geometry with radius.
 */
public abstract class RadialGeometry extends Geometry
{
	protected double _radius;
	
	// ***************** Constructors ********************** // 
	
	/**
	 * Default constructor
	 */
	public RadialGeometry() {
		super();
		this._radius = 0;
	}

	/**
	 * Copy constructor
	 */
	public RadialGeometry(RadialGeometry r) {
		this._radius = r.getRadius();
	}
	
	/**
	 * constructor
	 * @param radius of the geometry.
	 */
	public RadialGeometry(double _radius) {
		this._radius = _radius;
	}

	/**
	 * @param emmission of the geometry
	 * @param material of the geometry
	 * @param radius Geometry
	 */
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

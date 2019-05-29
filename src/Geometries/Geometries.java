package Geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;

/**
 * @author eitan
 * @info
 * class for list of geometries
 */
public class Geometries {

	private List<Geometry> _geometries;

	// ***************** Constructors ********************** //

	/**
	 * constructor
	 * @param geometries list of Geometry
	 */
	public Geometries(List<Geometry> geometries) {
		this._geometries = geometries;
	}

	/**
	 * Copy constructor
	 */
	public Geometries(Geometries geometries) {
		this._geometries = geometries.getGeometries();
	}

	/**
	 * Default constructor
	 */
	public Geometries() {
		_geometries = new ArrayList<Geometry>();
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * @return List of Geometry
	 */
	public List<Geometry> getGeometries() {
		return _geometries;
	}

	/**
	 * @param geometries list of the geometry
	 */
	public void setGeometries(List<Geometry> geometries) {
		this._geometries = geometries;
	}

	// ***************** Operations ******************** //

	/**
	 * function for add geometry for the list
	 * @param G few Geometries for add to the list 
	 */
	public void add(Geometry... G)
	{
		for(Geometry g:G)
		{
			this._geometries.add(g);
		}
		
	}
	/* (non-Javadoc)
	 * @see Geometries.Intersectable#findIntersections(primitives.Ray)
	 */
	//@Override
	//public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
	//	Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
	//	for (Intersectable geometry : _geometries) {
	//		Map<Geometry, List<Point3D>> geometryIntersectionPoints = geometry.findIntersections(ray);
	//		if (!geometryIntersectionPoints.isEmpty()) {
	//			for (Map.Entry<Geometry, List<Point3D>> geometry1 : geometryIntersectionPoints.entrySet()) {
	//				intersectionPoints.put(geometry1.getKey(), geometry1.getValue());
	//			}
	//		}
	//	}
	//	return intersectionPoints;
	//}

	// ***************** Admin ********************** //

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}

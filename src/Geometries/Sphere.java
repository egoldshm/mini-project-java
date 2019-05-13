package Geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * @author eitan A class to represent Sphere. realizes Geometry and inherits
 *         from RadialGeometry
 */
public class Sphere extends RadialGeometry {
	
	private Point3D _center;

	// ***************** Constructors ********************** //

	public Sphere(Point3D p, double r) {
		this._center = p;
		this._radius = r;
	}

	/**
	 * copy constructor
	 */
	public Sphere(Sphere sphere) {
		this._center = sphere.getCenter();
		this._radius = sphere.getRadius();
	}
	
	/**
	 * default constructor
	 */
	public Sphere() {
		super();
		_center = new Point3D();
		
	}
	
	// ***************** Getters/Setters ********************** //


	/**
	 * @return the center point of the Sphere
	 */
	public Point3D getCenter() {
		return _center;
	}

	/**
	 * @param p center point of the Sphere
	 */
	public void setCenter(Point3D p) {
		_center.setX(p.getX());
		_center.setY(p.getX());
		_center.setZ(p.getZ());
	}

	// ***************** Operations ******************** //

	/*
	 * (non-Javadoc)
	 * 
	 * @see Geometries.Geometry#findIntersections(primitives.Ray)
	 */
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray r) {

		Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
		ArrayList<Point3D> returnList = new ArrayList<Point3D>();
		// L = vector from the camera to center of the sphere
		Vector L = new Vector(_center.subtract(r.getPOO()));
		// If the ray goes through the center of the sphere, the geometry used to make
		// the general calculation does not work.
		// It does not create a triangle.
		// It is checked separately and the corresponding points of intersection are
		// returned.
		if (L.normalizationOfVector().equals(r.getDirection().normalizationOfVector())) {
			returnList.add(this._center.add(L.normalizationOfVector().scalarMultiplication(_radius)));
			returnList.add(this._center.add(L.normalizationOfVector().scalarMultiplication(-1 * _radius)));
			intersections.put(this, returnList);
			return intersections;
		}
		// Because the vector from the camera is the unit vector.
		// The result is the length of the projection of the L vector on the projection
		// vector.
		// That is, the length between the point of the camera and the point that is the
		// middle between the two cuts.
		double tm = L.scalarMultiplication(r.getDirection());
		// Finds the distance between the beginning of the sphere
		// and the point that is the center of the string of the ray that crosses the
		// circle
		// Using the Pythagoras theorem in a straight triangle, and the law that the
		// radius is perpendicular to the string and crosses it.
		double d = Math.sqrt(L.length() * L.length() - tm * tm);
		// System.out.println(d);
		// If the distance is greater than the sphere radius, there are no cutting
		// points.
		if (d > this._radius) {
			intersections.put(this, returnList);
			return intersections;
		}
		// If the distance is equal - there is one cut point
		if (d == this._radius) {
			// The point is the tm distance and the direction of the beam.
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(tm)));
			intersections.put(this, returnList);
			return intersections;
		}
		// The distance between the cut points and the middle of the string
		double th = Math.sqrt(this._radius * this._radius - d * d);
		// The distance from the starting point of each cut point (existing 2)
		double t1 = tm - th, t2 = tm + th;
		if (t1 > 0) {
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t1)));
		}
		if (t2 > 0) {
			returnList.add(r.getPOO().add(r.getDirection().scalarMultiplication(t2)));
		}
		intersections.put(this, returnList);
		return intersections;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Geometries.Geometry#getNormal(primitives.Point3D)
	 */
	@Override
	public Vector getNormal(Point3D point) {
		return point.subtract(this._center).normalizationOfVector();
	}

	// ***************** Admin ********************** //
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see Geometries.RadialGeometry#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object s) {
		// self check
		if (this == s)
			return true;
		// null check
		if (s == null)
			return false;
		// type check and cast
		if (getClass() != s.getClass())
			return false;
		return Objects.equals(_radius, ((RadialGeometry) s)._radius) && _center.equals(((Sphere) s)._center);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Geometries.RadialGeometry#toString()
	 */
	@Override
	public String toString() {
		return _center.toString() + " " + Double.toString(_radius);
	}

}

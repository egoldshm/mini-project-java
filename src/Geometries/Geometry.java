/**
 * 
 */
package Geometries;

import java.awt.Color;
import java.util.*;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author egoldshm
 * Interface for basic geometry
 */
public abstract class Geometry {
	
Color emmission;
	
/**
 * A function that finds cutting points between a ray and geometry
 * 
 * @param ray The ray with which we are looking for Intersection points
 * @return Cutting points with the ray
 */
abstract Map<Geometry,List<Point3D>> findIntersections(Ray r);

/**
 * A function that find the vector normal of geometry in point
 * 
 * @param point on the geometry in the normal
 * @return the vector of the normal
 */
abstract Vector getNormal(Point3D point);

/**
 * @return the emmission
 */
public Color getEmmission() {
	return emmission;
}

/**
 * @param emmission the emmission to set
 */
public void setEmmission(Color emmission) {
	this.emmission = emmission;
}
}

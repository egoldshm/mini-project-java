/**
 * 
 */
package Geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author egoldshm
 * Interface for basic geometry
 */
public abstract class Geometry {
	
Color _emmission;
	
/**
 * A function that finds cutting points between a ray and geometry
 * 
 * @param ray The ray with which we are looking for Intersection points
 * @return Cutting points with the ray
 */
abstract ArrayList<Point3D> findIntersections(Ray r);
}

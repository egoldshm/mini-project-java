/**
 * 
 */
package Geometries;

import java.util.ArrayList;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author egoldshm
 * Interface for basic geometry
 */
public interface Geometry {
	
/**
 * A function that finds cutting points between a ray and geometry
 * 
 * @param ray The ray with which we are looking for Intersection points
 * @return Cutting points with the beam
 */
ArrayList<Point3D> findIntersections(Ray ray);
}

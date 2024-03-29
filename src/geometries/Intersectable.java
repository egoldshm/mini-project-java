package geometries;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author egoldshm
 * interface for class that exist findIntersections function
 */
public interface Intersectable {

 /**
  * A function that finds cutting points between a ray and geometry
  *
  * @param ray The ray with which we are looking for Intersection points
  * @return Cutting points with the ray
  */
 List<Point3D>  findIntersections(Ray ray);
}

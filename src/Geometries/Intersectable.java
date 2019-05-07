/**
 *
 */
package Geometries;
import primitives.Point3D;
import primitives.Ray;

import java.util.Map;
import java.util.List;

/**
 * @author egoldshm
 *
 */
public interface Intersectable {
 Map<Geometry,List<Point3D>>  findIntersections(Ray ray);
}

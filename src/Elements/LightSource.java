/**
 * 
 */
package Elements;
import primitives.Vector;

import java.awt.Color;

import primitives.Point3D;
/**
 * @author eitan
 * interface for source of light 
 */
public interface LightSource {
 /**
  * function for get intensity in point
 * @param point where to find color
 * @return color in the point.
 */
Color getIntensity(Point3D point);
 /**
  * function that return Vector between light to point
 * @param point where to find vector
 * @return the vector
 */
Vector getL(Point3D point);
}

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
 Color getIntensity(Point3D point);
 Vector getL(Point3D point);
}

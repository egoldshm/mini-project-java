package UnitTests;
import primitives.*;
import Geometries.*;
import static org.junit.Assert.*;


import org.junit.Test;
/**
 * test cases for sphere
 */
public class SphereTest {

	/**
	 * TC for RayIntersections
	 * first case there is no intersections
	 */
	@Test
	public void  testRayIntersections() {
	Sphere testSphere=new Sphere(new Point3D(0,0,400),200)	;
	Ray r =new Ray(new Point3D(0,0,0),new Vector(0.577350269,-0.577350269,-0.577350269));
	assertEquals(0,testSphere.findIntersections(r).size());	
	}

}

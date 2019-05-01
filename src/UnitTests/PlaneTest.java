package UnitTests;
import primitives.*;
import Geometries.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * test cases for plane
 */
public class PlaneTest {

	/**
	 * TC for RayIntersections
	 */
	@Test
	public void testRayIntersections() {
		Plane testPlane = new Plane(new Vector(12,-4,1),new Point3D(0,0,4));
		Point3D pTest =new Point3D(1.29,4.43,6.29);
		Ray testRay= new Ray(new Point3D(1,1,4),new Vector(-1,-12,-8));	
		assertEquals(true,(testPlane.findIntersections(testRay)).get(0).equals(pTest));
				
	}

}

package unitTests;
import primitives.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.*;
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
		Point3D pTest =new Point3D(1.2857,4.4287,6.2857);
		Ray testRay= new Ray(new Point3D(1,1,4),new Vector(-1,-12,-8));	
		assertEquals(true,(testPlane.findIntersections(testRay)).get(0).equals(pTest));
				
	}
	
	@Test
	public void testNormal()
	{
		//some plane for which we check its normal at a point we know is on it
		Plane testPlane = new Plane(new Vector(1, -2, 4), new Point3D(0, 4, -1));
		assertEquals(testPlane.getNormal(new Point3D(0, 4, -1)), new Vector(1, -2, 4).normalizationOfVector());
	}

}

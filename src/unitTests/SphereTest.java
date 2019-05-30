package unitTests;
import primitives.*;

import static org.junit.Assert.*;


import org.junit.Test;

import geometries.*;
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
	//vector does not intersect at all - zero intersection points
	Sphere testSphere=new Sphere(new Point3D(0,4,0), 1)	;
	Ray r =new Ray(Point3D.ZERO,new Vector(0,0,1));
	assertEquals(0,testSphere.findIntersections(r).size());
	
	//vector is inside the sphere - one intersection point
	testSphere=new Sphere(Point3D.ZERO,200);
	assertEquals(1,testSphere.findIntersections(r).size());
	
	//vector begins on the outside and goes through the center of the sphere - two intersection points
	testSphere=new Sphere(new Point3D(0,0,300),200);
	r = new Ray(Point3D.ZERO, new Vector(0, 0, 100));
	assertEquals(2,testSphere.findIntersections(r).size());
		
	

	//vector begins on the outside and does not go through the center of the sphere - two intersection points
	testSphere=new Sphere(new Point3D(0,6,0),3);
	r = new Ray(Point3D.ZERO, new Vector(1, 4, 2));
	assertEquals(2,testSphere.findIntersections(r).size());
	}
	
	@Test
	public void testNormal()
	{
		//some sphere for which we check the normal at a point for which we know is on it
		Sphere testSphere = new Sphere(new Point3D(1, -1, 1), 2);
		assertEquals(testSphere.getNormal(new Point3D(1, -1, 3)), new Vector(0, 0, 1));
	}

}

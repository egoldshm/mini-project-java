package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import primitives.*;
import primitives.Point3D;
import Geometries.*;
/**
 * Test cases for triangle 
 */
public class TriangleTest {

	/**
	 * TC for RayIntersections
	 */
	@Test
	public void testRayIntersections() {
	//an example of a triangle and a vector that don't intersect, so there are no intersections.
	Triangle testTriangle = new Triangle(new Point3D(4, 0, 0), new Point3D(0, 4, 0), new Point3D(0, 0, 4));;
	Ray r =new Ray(new Point3D(0,0,0),new Vector(-2, 0, 0));
	List<Point3D> testArrayList= testTriangle.findIntersections(r);
	assertEquals(0,testArrayList.size());
	
	//an example of a triangle and a vector that intersect, so there is one intersection.
	r.setDirection(new Vector(2, 2, 2));	
	testArrayList = testTriangle.findIntersections(r);
	assertEquals(1,testArrayList.size());

	}
	
	@Test
	public void testNormal()
	{
		//some triangle in space
		Triangle testTriangle = new Triangle(Point3D.ZERO, new Point3D(0, 5, 0), new Point3D(-5, 0, 0));
		Point3D p = new Point3D(-2, -2, 0);
		assertEquals(testTriangle.getNormal(p), new Vector(0, 0, 1).normalizationOfVector());
		
		//some triangle in space
		testTriangle = new Triangle(Point3D.ZERO, new Point3D(-2, 4, 1), new Point3D(-4, 0, 0));
		p = new Point3D(-2, 1, 0.25);
		assertEquals(testTriangle.getNormal(p), new Vector(0, -4, 16).normalizationOfVector());
	}
}

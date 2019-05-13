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
		Triangle testTriangle = new Triangle(new Point3D(4, 0, 0), new Point3D(0, 4, 0), new Point3D(0, 0, 4));
	}
}

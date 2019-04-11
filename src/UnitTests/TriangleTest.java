package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	Triangle testTriangle=new Triangle(new Point3D(0,100,-200),new Point3D(100,-100,-200),new Point3D(-100,-100,-200));
	Ray r =new Ray(new Point3D(0,0,0),new Vector(0.577350269,-0.577350269,-0.577350269));
	ArrayList<Point3D> testArrayList= testTriangle.findIntersections(r);
	assertEquals(0,testArrayList.size());
	}

}

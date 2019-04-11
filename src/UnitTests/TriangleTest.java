package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import primitives.*;
import Geometries.*;
public class TriangleTest {

	@Test
	public void test() {
	Triangle testTriangle=new Triangle(new Point3D(0,100,-200),new Point3D(100,-100,-200),new Point3D(-100,-100,-200));
	Ray r =new Ray(new Point3D(0,0,0),new Vector(0.577350269,-0.577350269,-0.577350269));
	ArrayList testArrayList= testTriangle.findIntersections(r);
	assertEquals(0,testArrayList.size());
	}

}

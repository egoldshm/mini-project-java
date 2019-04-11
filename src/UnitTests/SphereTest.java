package UnitTests;
import primitives.*;
import Geometries.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({ Point3DTest.class, VectorTest.class })

public class SphereTest {

	@Test
	public void  testRayIntersections() {
	Sphere testSphere=new Sphere(new Point3D(0,0,400),200)	;
	Ray r =new Ray(new Point3D(0,0,0),new Vector(0.577350269,-0.577350269,-0.577350269));
	assertEquals(0,testSphere.findIntersections(r).size());	
	}

}

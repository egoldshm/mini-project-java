package UnitTests;
import primitives.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import Elements.Camera;
@RunWith(Suite.class)
@SuiteClasses({ Point3DTest.class, VectorTest.class })
public class CameraTest
{
	@Test
	public void testConstructRayThroughPixel()
	{
		Camera testCamera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(1, 0, 0), new Vector(0, 1, 0));
		int Nx = 5, Ny = 5;
		double x = 3, y = 3, screenDistance = 1, screenWidth = 5, screenHeight = 5;
		assertEquals(testCamera.constructRayThroughPixel(Nx, Ny, x, y, screenDistance, screenWidth, screenHeight), new Ray(Point3D.ZERO, new Vector(1, 0, 0)));
	}
}

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
		Camera testCamera = new Camera(Point3D.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1), new Vector(1, 0, 0));
		int Nx = 3, Ny = 3;
		double x = 3, y = 3, screenDistance = 100, screenWidth = 150, screenHeight = 150;
		assertEquals(testCamera.constructRayThroughPixel(Nx, Ny, x, y, screenDistance, screenWidth, screenHeight), new Ray(Point3D.ZERO, new Vector(50, -50, -100).normalizationOfVector()));
	}
}

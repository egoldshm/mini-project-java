package unitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CameraTest.class, PlaneTest.class, Point3DTest.class, SphereTest.class, TriangleTest.class,
		VectorTest.class, ImageWriterTest.class, TestCoordinate.class, TestPoint2D.class, TestPoint3D.class, renderTest.class})
public class AllTests {

}

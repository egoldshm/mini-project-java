package unitTests;

import primitives.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author eitan
 * @info 
 *  Test cases for class Point3D
 */
public class Point3DTest
{
	/**
	 * Test case for subtract 2 point3D
	 */
	@Test
	public void testSubtract()
	{
		Point3D p1 = new Point3D(1, 2, 3), p2 = new Point3D(-3, -2, -1);
		assertEquals(p1.subtract(p2), new Vector(4, 4, 4));
		assertEquals(p2.subtract(p1), new Vector(-4, -4, -4));
		p1 = new Point3D(-1, -2, -3);
		p2 = new Point3D(-3, -2, -1);
		assertEquals(p1.subtract(p2), new Vector(2, 0, -2));
		assertEquals(p2.subtract(p1), new Vector(-2, 0, 2));
	}
	
	/**
	 * TC for add 2 point3D
	 */
	@Test
	public void testAdd()
	{
		Point3D p = new Point3D(1, 1, 1);
		Vector v = new Vector(1, 1, 1);
		assertEquals(Point3D.ZERO.add(v), p);
		assertEquals(p.add(v), new Point3D(2, 2, 2));
		p = new Point3D(-1, -1, -1);
		assertEquals(p.add(v), Point3D.ZERO);
		v= new Vector(-1, -1, -1);
		assertEquals(p.add(v), new Point3D(-2, -2, -2));
	}
	
	/**
	 * TC for test multiply 2 point3D
	 */
	@Test
	public void testMultiply()
	{
		Point3D p = new Point3D(1, 1, 1);
		assertEquals(p.multiply(0), Point3D.ZERO);
		assertEquals(Point3D.ZERO.multiply(7), Point3D.ZERO);
		assertEquals(p.multiply(2), new Point3D(2, 2, 2));
		assertEquals(p.multiply(-1), new Point3D(-1, -1, -1));
	}
	
	/**
	 * TC for Squared Distance for point3D
	 */
	@Test
	public void testSquaredDistance()
	{
		Point3D p1 = new Point3D(1, 1, 1), p2 = new Point3D(2, 2, 2);
		assertEquals(Point3D.ZERO.squaredDistance(p1), 3, 0.01);
		assertEquals(p2.squaredDistance(p1), 3, 0.01);
		assertEquals(p1.squaredDistance(p1), 0, 0.01);
		p2 = new Point3D(-1, -1, -1);
		assertEquals(p1.squaredDistance(p2), 12, 0.01);
	}
	
	/**
	 * TC for distance between 2 point3D
	 */
	@Test
	public void testDistance()
	{
		Point3D p1 = new Point3D(1, 1, 1), p2 = new Point3D(2, 2, 2);
		assertEquals(Point3D.ZERO.distance(p1), Math.sqrt(3), 0.01);
		assertEquals(p2.distance(p1), Math.sqrt(3), 0.01);
		assertEquals(p1.distance(p1), 0, 0.01);
		p2 = new Point3D(-1, -1, -1);
		assertEquals(p1.distance(p2), Math.sqrt(12), 0.01);
	}
}

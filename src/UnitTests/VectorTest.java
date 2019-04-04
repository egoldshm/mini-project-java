package UnitTests;

import primitives.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class VectorTest 
{
	@Test
	public void testNormalize()
	{
		assertEquals(1,0);
		Vector v = new Vector(5, 2, 3);
		assertEquals(1, v.normalizationOfVector().length(), 0.01);		
		try {
			v = new Vector(0, 0, 0);
			v = v.normalizationOfVector();
			fail("Didn't throw divide by zero exception!");
		} catch(ArithmeticException e) {
			assertTrue(true);
		}
		v = new Vector(5, -7, 3);
		assertEquals(1, v.normalizationOfVector().length(), 0.01);	
		v = new Vector(5, 7, -3);
		assertEquals(1, v.normalizationOfVector().length(), 0.01);	
	}
	
	@Test
	public void testAddVector() {
		Vector v1 = new Vector(1, 2, 3), v2 = new Vector(3, 2, 1);
		assertEquals(v1.addVector(v2), new Vector(4, 4, 4));
		assertEquals(v2.addVector(v1), new Vector(4, 4, 4));
		v1 = new Vector(-1, -2, -3);
		v2 = new Vector(-3, -2, -1);
		assertEquals(v1.addVector(v2), new Vector(-4, -4, -4));
		assertEquals(v2.addVector(v1), new Vector(-4, -4, -4));
	}
	
	@Test
	public void testSubtractVector()
	{
		Vector v1 = new Vector(1, 2, 3), v2 = new Vector(-3, -2, -1);
		assertEquals(v1.subtractVector(v2), new Vector(4, 4, 4));
		assertEquals(v2.subtractVector(v1), new Vector(-4, -4, -4));
		v1 = new Vector(-1, -2, -3);
		v2 = new Vector(-3, -2, -1);
		assertEquals(v1.subtractVector(v2), new Vector(2, 0, -2));
		assertEquals(v2.subtractVector(v1), new Vector(-2, 0, 2));
	}
	
	
	@Test
	public void testScaleVector()
	{
		Vector v = new Vector(1, 1, 1);
		assertEquals(v.scalarMultiplication(-2), new Vector(-2, -2, -2));
		assertEquals(v.scalarMultiplication(2), new Vector(2, 2, 2));
		v = new Vector(-1, -1, -1);
		assertEquals(v.scalarMultiplication(2), new Vector(-2, -2, -2));
		assertEquals(v.scalarMultiplication(-2), new Vector(2, 2, 2));
	}
	
	@Test
	public void testScalarMultiplication()
	{
		Vector v1 = new Vector(1, 1, 1), v2 = new Vector(2, 2, 2);
		assertEquals(v1.scalarMultiplication(v2), 6, 0.01);
		assertEquals(v2.scalarMultiplication(v1), 6, 0.01);
		v1 = new Vector(-1, -1, -1);
		assertEquals(v1.scalarMultiplication(v2), -6, 0.01);
		assertEquals(v2.scalarMultiplication(v1), -6, 0.01);
	}
	

	@Test
	public void testCrossProductMultiplication()
	{
		Vector v1 = new Vector(0, 0, 1), v2 = new Vector(0, 1, 0);
		assertEquals(v1.CrossProductVector(v2), new Vector(1, 0, 0));
		assertEquals(v2.CrossProductVector(v1), new Vector(-1, 0, 0));
		v1 = new Vector(1, 1, 0);
		v2 = new Vector(0, 1, 1);
		assertEquals(v1.CrossProductVector(v2), new Vector(1, -1, 1));
		assertEquals(v2.CrossProductVector(v1), new Vector(-1, 1, -1));
	}
	
	

}

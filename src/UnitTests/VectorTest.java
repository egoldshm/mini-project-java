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
		Vector v = new Vector(5, 0, 3);
		v = v.normalizationOfVector();
		assertEquals(1, v.length(), 0.01);
		v = new Vector(0, 0, 0);
		try {
			v = v.normalizationOfVector();
			fail("Didn't throw divide by zero exception!");
		} catch(ArithmeticException e) {
			assertTrue(true);
		}
	}

}

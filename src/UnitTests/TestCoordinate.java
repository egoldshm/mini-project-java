package UnitTests;

import static org.junit.Assert.*;

import primitives.Coordinate;

import org.junit.Test;

/**
 * @author egoldshm
 *
 */
public class TestCoordinate {

	@Test
	public void test(){
		Coordinate con2 = new Coordinate(1);
		con2.setNum(15);
		assertEquals(con2.getNum(),15.0d,0);
		}
	@Test
	public void testAdd(){
		Coordinate con2 = new Coordinate(1);
		Coordinate con1 = new Coordinate(5);
		Coordinate con3 = con1.add(con2);
		assertEquals(con3.getNum(),6.0d,0);
		}

	@Test
	public void testSubtract() {
		Coordinate con2 = new Coordinate(2);
		Coordinate con1 = new Coordinate(1);
		assertEquals(con2.subtract(con1).getNum(),1.0d,0);
	}

	@Test
	public void testScale() {
		Coordinate con1 = new Coordinate(1);
		assertEquals(con1.scale(8).getNum(),8.0d,0);	}

	@Test
	public void testMultiplyCoordinate() {
		Coordinate con2 = new Coordinate(1);
		Coordinate con1 = new Coordinate(2);
		assertEquals(con1.multiply(con2).getNum(),2.0d,0);
	}

	@Test
	public void testMultiplyDouble() {
		Coordinate con1 = new Coordinate(2);
		assertEquals(con1.multiply(5).getNum(),10.0d,0);
	}

}

package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point2D;
import primitives.Coordinate;;


public class TestPoint2D {

	@Test
	public void test() {
		Point2D p1 = new Point2D(new Coordinate(1.0d),new Coordinate(2.0d));
		assertEquals(p1.getX(),new Coordinate(1.0d));
		assertEquals(p1.getY(),new Coordinate(2.0d));
		p1.setX(new Coordinate(10));
		p1.setY(new Coordinate(15));
		assertEquals(p1.getX(),new Coordinate(10.0d));
		assertEquals(p1.getY(),new Coordinate(15.0d));
	}

}

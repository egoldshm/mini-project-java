package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Elements.*;
import Geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import renderer.Render.*;
import Scene.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class renderTest {

	@Test
	public void basicRenderTest() {
		List<Geometry> geometries = new ArrayList<Geometry>();
        geometries.add(new Triangle(new Point3D( 100, 0, 149),new Point3D(  0, 100, 149),new Point3D( 100, 100, 149)));
		geometries.add(new Sphere(new Point3D(0, 0, 150), 50));
	    geometries.add(new Triangle(new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149)));	
		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0, 100, 149),new Point3D(-100, 100, 149)));	
		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, 1),new Vector(0, 1, 0)), 100);
		ImageWriter imageWriter = new ImageWriter("tests/test0", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);	
		render.renderImage();
		render.printGrid(50);
		render.get_imageWriter().writeToimage();
	}

	@Test
	public void colorRenderTest() {
		List<Geometry> geometries = new ArrayList<Geometry>();
		geometries.add(new Triangle(new Color(0,255,0),null,new Point3D( 100, 0, 149),new Point3D(  0, 100, 149),new Point3D( 100, 100, 149)));
		geometries.add(new Sphere(new Color(0,0,0),null,50,new Point3D(0, 0, 150)));
		geometries.add(new Triangle(new Color(255,0,0),null, new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149)));
		geometries.add(new Triangle(new Color(0,0,255),null,new Point3D(-100, 0, 149), new Point3D(  0, 100, 149),new Point3D(-100, 100, 149)));
		geometries.add(new Triangle(new Color(255, 252,0),null,new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, 1),new Vector(0, 1, 0)), 100);
		ImageWriter imageWriter = new ImageWriter("tests/colorTest", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		render.get_imageWriter().writeToimage();
	}

}

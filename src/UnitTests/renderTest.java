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

	/*@Test
	public void basicRenderTest() {
		List<Geometry> geometries = new ArrayList<Geometry>();
        geometries.add(new Triangle(new Point3D( 100, 0, 149),new Point3D(  0, 100, 149),new Point3D( 100, 100, 149)));
		geometries.add(new Sphere(new Point3D(0, 0, 150), 50));
	    geometries.add(new Triangle(new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149)));	
		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0, 100, 149),new Point3D(-100, 100, 149)));	
		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, 1),new Vector(0, 1, 0)), 100);
		ImageWriter imageWriter = new ImageWriter("tests/blackAndWhiteTest", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);	
		render.renderImage();
		render.printGrid(50);
		render.get_imageWriter().writeToimage();
	}

	@Test
	public void colorRenderTest() {
		List<Geometry> geometries = new ArrayList<Geometry>();
		geometries.add(new Triangle(new Color(106,206,0),new Material(),new Point3D( 100, 0, 149),new Point3D(  0, 100, 149),new Point3D( 100, 100, 149)));
		geometries.add(new Sphere(new Color(32,22,0),new Material(),50,new Point3D(0, 0, 150)));
		geometries.add(new Triangle(new Color(255,0,0),new Material(), new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149)));
		geometries.add(new Triangle(new Color(32,22,0),new Material(),new Point3D(-100, 0, 149), new Point3D(  0, 100, 149),new Point3D(-100, 100, 149)));
		geometries.add(new Triangle(new Color(0, 0,255),new Material(),new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(170, 170, 170), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, 1),new Vector(0, 1, 0)), 100);
		ImageWriter imageWriter = new ImageWriter("tests/colorTest", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		render.get_imageWriter().writeToimage();
	}*/
	
	@Test
	public void specAndDifTest1() {
		//two triangles and a pointlight
		Geometries geo = new Geometries();
		geo.add(new Triangle(new Color(32,22,0),new Material(), new Point3D(100, 50, 50), new Point3D(100, -50, 50),new Point3D(100, -50, -50)));
		geo.add(new Triangle(new Color(32,22,0),new Material(), new Point3D(100, 50, 50), new Point3D(100, 50, -50),new Point3D(100, -50, -50)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0,0),new Vector(0, 1, 0)), 400);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new pointLight(new Color(0,0,255),new Point3D(95,0,0), 0.01,0.01,0.01));
		scene.setLights(lights);
		ImageWriter imageWriter = new ImageWriter("tests/specAndDifTest1", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		//a sphere and a pointlight
		geo = new Geometries();
		geo.add(new Sphere(new Color(32,22,0),new Material(), 100, new Point3D(200, 0, 0)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 0, 255), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0, 0),new Vector(0, 1, 0)), 400);
		lights = new ArrayList<LightSource>();
		lights.add(new pointLight(new Color(255,255,255),new Point3D(95,0,0), 0.01,0.01,0.01));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest2", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		//two triangles and a spotlight
		geo = new Geometries();
		geo.add(new Triangle(new Color(32,22,0),new Material(), new Point3D(100, 50, 50), new Point3D(100, -50, 50),new Point3D(100, -50, -50)));
		geo.add(new Triangle(new Color(32,22,0),new Material(), new Point3D(100, 50, 50), new Point3D(100, 50, -50),new Point3D(100, -50, -50)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 0, 255), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0, 0),new Vector(0, 1, 0)), 400);
		lights = new ArrayList<LightSource>();
		lights.add(new spotLight(new Color(255,255,255),new Point3D(95,0,0), 0.01,0.01,0.01, new Vector(1, 0, 0)));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest3", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();

		//a sphere and a spotlight
		geo = new Geometries();
		geo.add(new Sphere(new Color(32,22,0),new Material(), 100, new Point3D(200, 0, 0)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 0, 255), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0, 0),new Vector(0, 1, 0)), 400);
		lights = new ArrayList<LightSource>();
		lights.add(new spotLight(new Color(255,255,255),new Point3D(95,0,0), 0.01,0.01,0.01, new Vector(1, 0, 0)));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest4", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
	}
}
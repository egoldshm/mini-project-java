package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import renderer.Render.*;
import scene.*;
import elements.*;
import geometries.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class renderTest {

	@Test
	public void recursiveTest()
       {
		Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(1,1,20,0,0.5),500, new Point3D(0.0, 0.0, -1000));
		Sphere sphere2 = new Sphere(new Color(100, 20, 20), new Material(1,1,20,0,0),250, new Point3D(0.0, 0.0, -1000));
		List<LightSource> list = new ArrayList<LightSource>();
		list.add(new spotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150) ,0.1,0.00001, 0.000005, new Vector(2, 2, -3)));
	
		ImageWriter imageWriter = new ImageWriter("tests/RecursiveTest11", 500, 500, 500, 500);
		Geometries geo = new Geometries();
		geo.add(sphere,sphere2);
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 0.1), geo, new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, -1),new Vector(0, 1, 0)), 300,list);
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();
	}
	
	
	@Test
	public void recursiveTest2(){
		
				
		Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(1,1,20,0,0.5),300, new Point3D(-550, -500, -1000));
		Sphere sphere2 = new Sphere(new Color(100, 20, 20), new Material(1,1,20,0,0),150, new Point3D(-550, -500, -1000));

		Triangle triangle = new Triangle(new Color(20, 20, 20),new Material(1,1,4,1,0),
				new Point3D(1500, -1500, -1500),
				 						 new Point3D( -1500,  1500, -1500),
				 						 new Point3D(  200,  200, -375));

		Triangle triangle2 = new Triangle(new Color(20, 20, 20),new Material(1,1,4,0.5,0),
				new Point3D(  1500, -1500, -1500),
										  new Point3D( -1500,  1500, -1500),
										  new Point3D( -1500, -1500, -1500));
		
		List<LightSource> list = new ArrayList<LightSource>();
		list.add(new spotLight(new Color(255, 100, 100),
				new Point3D(200, 200, -150), 0, 0.00001, 0.000005, new Vector(-2, -2, -3)));
		Geometries geo = new Geometries();
		geo.add(triangle,triangle2,sphere,sphere2);
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 0.1), geo, new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, -1),new Vector(0, 1, 0)), 300,list);

		ImageWriter imageWriter = new ImageWriter("tests/RecursiveTest2", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
	}
	
	@Test
	public void recursiveTest3(){
				
		Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(1,1,20,0,0.5),300, new Point3D(0, 0, -1000));
		Sphere sphere2 = new Sphere(new Color(100, 20, 20), new Material(1,1,20,0,0),150, new Point3D(0, 0, -1000));		
		
		Triangle triangle = new Triangle(new Color(20, 20, 20),new Material(1,1,4,1,0)
				,new Point3D(  2000, -1000, -1500),
				 						 new Point3D( -1000,  2000, -1500),
				 						 new Point3D(  700,  700, -375));
		
		Triangle triangle2 = new Triangle(new Color(20, 20, 20),new Material(1,1,4,0.5,0)
				,new Point3D(  2000, -1000, -1500),
										  new Point3D( -1000,  2000, -1500),
										  new Point3D( -1000, -1000, -1500));
		Geometries geo = new Geometries();
		geo.add(triangle,triangle2,sphere,sphere2);

		List<LightSource> list = new ArrayList<LightSource>();
		list.add(new spotLight(new Color(255, 100, 100), new Point3D(200, 200, -150), 0, 0.00001, 0.000005, new Vector(-2, -2, -3)));
		
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(255, 255, 255), 0.1), geo, new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, -1),new Vector(0, 1, 0)), 300,list);

		ImageWriter imageWriter = new ImageWriter("tests/RecursiveTest3", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();

	}
	
	
	@Test
	public void shadowTest(){
		
		//test where the triangle is further away from the sphere
		List<Geometry> geometries = new ArrayList<Geometry>();
		Sphere sphere = new Sphere(new Color(0,0,100), new Material(1,1,20) , 500, new Point3D(0.0, 0.0, -1000));
		
		
		Triangle triangle = new Triangle(new Color(0, 0, 100), new Material(1, 1, 20), new Point3D( -125, -225, -260),
							 new Point3D( -225, -125, -260),
				 						 new Point3D(  -225, -225, -270));
		geometries.add(sphere);
		geometries.add(triangle);
		
		List<LightSource> lst = new ArrayList<LightSource>();
		lst.add(new spotLight(new Color(100, 0, 100), new Point3D(-200, -200, -150), 
				    0, 0.000001, 0.0000005,new Vector(2, 2, -3)));
	
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, -1),new Vector(0, 1, 0)), 500);
		scene.setLights(lst);
		ImageWriter imageWriter = new ImageWriter("tests/shadowTestFarTriangleFarLight", 1000, 1000, 1000, 1000);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		
		geometries.remove(triangle);
		triangle.setP1(triangle.getP1().add(new Vector(0, 0, -100)));
		triangle.setP2(triangle.getP2().add(new Vector(0, 0, -100)));
		triangle.setP3(triangle.getP3().add(new Vector(0, 0, -100)));
		geometries.add(triangle);
		imageWriter = new ImageWriter("tests/shadowTestCloseTriangleFarLight", 1000, 1000, 1000, 1000);
		render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		geometries.remove(triangle);
		triangle.setP1(triangle.getP1().add(new Vector(0, 0, 100)));
		triangle.setP2(triangle.getP2().add(new Vector(0, 0, 100)));
		triangle.setP3(triangle.getP3().add(new Vector(0, 0, 100)));
		geometries.add(triangle);
		lst.clear();
		lst.add(new spotLight(new Color(100, 0, 100), new Point3D(-200, -200, -150).add(new Vector(2, 2, -3).scalarMultiplication(5)), 
			    0, 0.000001, 0.0000005,new Vector(2, 2, -3)));
		imageWriter = new ImageWriter("tests/shadowTestFarTriangleCloseLight", 1000, 1000, 1000, 1000);
		render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.get_imageWriter().writeToimage();
	}
	
	@Test
	public void basicRenderTest() {
		//four triangles and a sphere without colors
		//file "blackAndWhiteTest"
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
		//four triangles and a sphere with colors
		//file "colorTest"
		List<Geometry> geometries = new ArrayList<Geometry>();
		geometries.add(new Triangle(new Color(106,206,0),new Material(),new Point3D( 100, 0, 149),new Point3D(  0, 100, 149),new Point3D( 100, 100, 149)));
		geometries.add(new Sphere(new Color(50,50,50),new Material(),50,new Point3D(0, 0, 150)));
		geometries.add(new Triangle(new Color(255,0,0),new Material(), new Point3D( 100, 0, 149), new Point3D(  0, -100, 149), new Point3D( 100,-100, 149)));
		geometries.add(new Triangle(new Color(50,50,50),new Material(),new Point3D(-100, 0, 149), new Point3D(  0, 100, 149),new Point3D(-100, 100, 149)));
		geometries.add(new Triangle(new Color(0, 0,255),new Material(),new Point3D(-100, 0, 149), new Point3D(  0,  -100, 149), new Point3D(-100, -100, 149)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geometries), new Camera(new Point3D(0, 0, 0),  new Vector(0, 0, 1),new Vector(0, 1, 0)), 100);
		ImageWriter imageWriter = new ImageWriter("tests/colorTest", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		render.get_imageWriter().writeToimage();
	}

	@Test
	public void specAndDifTest() {
		//two triangles and a pointlight
		//file specAndDifTest1
		Geometries geo = new Geometries();
		geo.add(new Triangle(new Color(121,83,90),new Material(10, 1, 4), new Point3D(100, 50, 50), new Point3D(100, -50, 50),new Point3D(100, -50, -50)));
		geo.add(new Triangle(new Color(121,83,90),new Material(10, 1, 4), new Point3D(100, 50, 50), new Point3D(100, 50, -50),new Point3D(100, -50, -50)));
		Scene scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0,0),new Vector(0, 1, 0)), 400);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new pointLight(new Color(175,29,105),new Point3D(95,0,0), 0.01,0.01,0.01));
		scene.setLights(lights);
		ImageWriter imageWriter = new ImageWriter("tests/specAndDifTest1", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		//a sphere and a pointlight
		//file specAndDifTest2
		geo = new Geometries();
		geo.add(new Sphere(new Color(0,0,100),new Material(10, 1, 4), 100, new Point3D(200, 0, 0)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0, 0),new Vector(0, 1, 0)), 400);
		lights = new ArrayList<LightSource>();
		lights.add(new pointLight(new Color(175,29,105),new Point3D(95,5,-50), 0.01,0.01,0.01));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest2", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
		//two triangles and a spotlight
		//file specAndDifTest3
		geo = new Geometries();
		geo.add(new Triangle(new Color(121,83,90),new Material(20, 1, 4), new Point3D(100, 50, 50), new Point3D(100, -50, 50),new Point3D(100, -50, -50)));
		geo.add(new Triangle(new Color(121,83,90),new Material(20, 1, 4), new Point3D(100, 50, 50), new Point3D(100, 50, -50),new Point3D(100, -50, -50)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(1, 0, 0),new Vector(0, 1, 0)), 400);
		lights = new ArrayList<LightSource>();
		lights.add(new spotLight(new Color(175,29,105),new Point3D(95, 0, 0), 0.01,0.01,0.01, new Vector(1, -1,0 )));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest3", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();

		//a sphere and a spotlight
		//file specAndDifTest4
		geo = new Geometries();
		geo.add(new Sphere(new Color(0,0,255),new Material(2, 1.5, 50), 500, new Point3D(0, 0, -1000)));
		scene = new Scene("Test scene", new Color(0, 0, 0), new AmbientLight(new Color(0, 0, 0), 1), new Geometries(geo), new Camera(Point3D.ZERO,  new Vector(0, 0, -1),new Vector(0, 1, 0)), 200);
		lights = new ArrayList<LightSource>();
		lights.add(new spotLight(new Color(255, 0, 255),new Point3D(116 , 146, -474), 0.01, 0.00001, 0.000005, new Vector(-2, -2, -3)));
		scene.setLights(lights);
		imageWriter = new ImageWriter("tests/specAndDifTest4", 500, 500, 500, 500);
		render = new Render(scene, imageWriter);
		render.renderImage();
		render.get_imageWriter().writeToimage();
		
	
 }
	
 }



	

package renderer;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import Scene.Scene;
import primitives.*;
import Geometries.*;


public class Render {
	
	/**
	 * default constructor
	 */
	public Render() {
	
	}
	
	
	/**
	 * constructor with params
	 * @param _scene
	 * @param _imageWriter
	 */
	public Render(Scene _scene, ImageWriter _imageWriter) {
		this._scene = _scene;
		this._imageWriter = _imageWriter;
	}
	
	/**
	 * copy constructor 
	 */
	public Render(Render r) {
		this._scene = r._scene;
		this._imageWriter = r._imageWriter;
	}
	
	/**
	 * @return Scene
	 */
	public Scene get_scene() {
		return _scene;
	}
	
	public void set_scene(Scene _scene) {
		this._scene = _scene;
	}
	
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}
	
	public void set_imageWriter(ImageWriter _imageWriter) {
		this._imageWriter = _imageWriter;
	}
	private List<Point3D> getSceneRayIntersections(Ray ray)
	{
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		List <Point3D> intersectionPoints = new ArrayList<Point3D>();
		while(geometries.hasNext())
		{
			Geometry geometry =geometries.next();
			List<Point3D>geometryIntersectionPoint=geometry.findIntersections(ray);
			intersectionPoints.addAll(geometryIntersectionPoint);
		}
		return intersectionPoints;
	}
	Scene _scene ; 
	ImageWriter _imageWriter ;
	
	private Color calcColor(Point3D point) {
		return _scene.get_ambientLight().getIntensity();
	}
	 
	
	

}

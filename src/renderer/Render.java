package renderer;

import java.util.*;

import Elements.LightSource;

import java.awt.Color;

import Scene.Scene;
import primitives.*;
import primitives.Vector;
import Geometries.*;

/**
 * class that render picture from sence and camera
 *
 */
public class Render {

	private Scene _scene;
	private ImageWriter _imageWriter;

	// ***************** Constructors ********************** //

	/**
	 * default constructor
	 */
	public Render() {

	}

	/**
	 * constructor with params
	 * 
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

	// ***************** Getters/Setters ********************** //
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

	// ***************** Operations ******************** //

	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
		while (geometries.hasNext()) {
			Geometry geometry = geometries.next();
			List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
			intersectionPoints.put(geometry,geometryIntersectionPoints);
		}
		return intersectionPoints;
	}

	/**
	 * @param geometry
	 * @param point
	 * @return
	 */
	private Color calcColor(Geometry geometry, Point3D point) {
		Color ambientLight = _scene.getAmbientLight().getIntensity(/*point*/);
		Color emissionLight = geometry.getEmmission();
		Color I0 = new Color (ambientLight.getRed() + emissionLight.getRed(),
				ambientLight.getGreen() + emissionLight.getGreen(),
				ambientLight.getBlue() + emissionLight.getBlue());
		return I0;
	}


	/**
	 * @param interval
	 */
	public void printGrid(int interval) {
		for (int i = 0; i < _imageWriter.getWidth(); i += interval) {
			for (int j = 0; j < _imageWriter.getHeight(); j++) {
				_imageWriter.writePixel(i, j, 255 - _scene.getBackground().getRed(),
						255 - _scene.getBackground().getGreen(), 255 - _scene.getBackground().getBlue());
			}
		}
		for (int i = 0; i < _imageWriter.getHeight(); i += interval) {
			for (int j = 0; j < _imageWriter.getWidth(); j++) {
				_imageWriter.writePixel(j, i, 255 - _scene.getBackground().getRed(),
						255 - _scene.getBackground().getGreen(), 255 - _scene.getBackground().getBlue());
			}
		}
	}

	/**
	 * @param intersectionPoints
	 * @return
	 */
	private Map<Geometry, Point3D>  getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
		// In the intersectionPoints - find the point with minimal distance from the ray
		// begin point and return it
		double distance = Double.MAX_VALUE;
		Point3D P0 = _scene.getCamera().getPO();

		Map<Geometry, Point3D> minDistancePoint =
				new HashMap<Geometry, Point3D>();
		for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
			for (Point3D point : entry.getValue()) {
				if (P0.distance(point) < distance) {
					distance = P0.distance(point);
					minDistancePoint.clear();
					minDistancePoint.put(entry.getKey(), new Point3D(point));
				}
			}
		}
		return minDistancePoint;
	}

	public void renderImage() {

		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				Ray r = new Ray(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),
						i, j, _scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
				Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(r);																													// fix
				if (intersectionPoints.isEmpty())
				{
					_imageWriter.writePixel(i, j, _scene.getBackground());
				}
				else
					{
					Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(i, j, this.calcColor(closestPoint.entrySet().iterator().next().getKey(),closestPoint.entrySet().iterator().next().getValue()));
				}
			}
		}
	}

	// ***************** Admin ********************** //

}

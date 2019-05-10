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

	private class GeoPoint {
		public Geometry geometry;
		public Point3D point;
	}

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

	// TODO: fix it function
	private List<Point3D> getSceneRayIntersections(Ray ray) {
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		List<Point3D> intersectionPoints = new ArrayList<Point3D>();
		while (geometries.hasNext()) {
			Intersectable geometry = geometries.next();
			Map<Geometry, List<Point3D>> geometryIntersectionPoint = geometry.findIntersections(ray);
			intersectionPoints.addAll(geometryIntersectionPoint);
		}
		return intersectionPoints;
	}

	/**
	 * @param geometry
	 * @param point
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint) {
		Color color = _scene.getAmbientLight().getIntensity();
		Vector v = geopoint.point.subtract(_scene.getCamera().getPO()).normalizationOfVector();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		int nShininess = geopoint.geometry.getMaterial().getnShininess();
		double kd = geopoint.geometry.getMaterial().getKd();
		double ks = geopoint.geometry.getMaterial().getKs();
		for (LightSource lightSource : this.get_scene().getLights()) {
			Vector l = lightSource.getL(geopoint.point);
			if (n.scalarMultiplication(l) * n.scalarMultiplication(v) > 0) {
				Color lightIntensity = lightSource.getIntensity(geopoint.point);
				color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}

		return new Color(geopoint.geometry.getEmmission().getRed() + color.getRed(),
				geopoint.geometry.getEmmission().getGreen() + color.getRed(),
				geopoint.geometry.getEmmission().getBlue() + color.getBlue());
	}

	private Object calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		// TODO Auto-generated method stub
		return null;
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
	private GeoPoint getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
		// In the intersectionPoints - find the point with minimal distance from the ray
		// begin point and return it
		double distance = Double.MAX_VALUE;
		Point3D P0 = _scene.getCamera().getPO();

		GeoPoint geopoint = new GeoPoint();
		for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
			for (Point3D point : entry.getValue()) {
				if (P0.distance(point) < distance) {
					distance = P0.distance(point);
					geopoint.point = point;
					geopoint.geometry = entry.getKey();
				}
			}
		}
		return geopoint;
	}

	public void renderImage() {

		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				Ray r = new Ray(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),
						i, j, _scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
				Map<Geometry, List<Point3D>> intersectionPoints = this.get_scene().getGeometries().findIntersections(r);// TODO:
																														// fix
				if (intersectionPoints.isEmpty()) {
					_imageWriter.writePixel(i, j, _scene.getBackground());
				} else {
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);// TODO: fix
					_imageWriter.writePixel(i, j, this.calcColor(closestPoint));
				}
			}
		}
	}

	// ***************** Admin ********************** //

}

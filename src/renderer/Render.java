package renderer;

import java.util.List;
import java.util.Map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import Scene.Scene;
import primitives.*;
import Geometries.*;

public class Render {

	private class GeoPoint {
		public Geometry geometry;
		public Point3D point;
	}

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

	// TODO: fix it function
	private List<Point3D> getSceneRayIntersections(Ray ray) {
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		List<Point3D> intersectionPoints = new ArrayList<Point3D>();
		while (geometries.hasNext()) {
			Geometry geometry = geometries.next();
			Map<Geometry, List<Point3D>> geometryIntersectionPoint = geometry.findIntersections(ray);
			intersectionPoints.addAll(geometryIntersectionPoint);
		}
		return intersectionPoints;
	}

	Scene _scene;
	ImageWriter _imageWriter;

	/**
	 * @param geometry
	 * @param point
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint) {
		Color color = _scene.get_ambientLight().getIntensity();
		
		
		return new Color(geopoint.geometry.getEmmission().getRed() + color.getRed(),
				geopoint.geometry.getEmmission().getGreen() + color.getRed(),
				geopoint.geometry.getEmmission().getBlue() + color.getBlue());
	}

	/**
	 * @param interval
	 */
	public void printGrid(int interval) {
		for (int i = 0; i < _imageWriter.getWidth(); i += interval) {
			for (int j = 0; j < _imageWriter.getHeight(); j++) {
				_imageWriter.writePixel(i, j, 255 - _scene.get_background().getRed(),
						255 - _scene.get_background().getGreen(), 255 - _scene.get_background().getBlue());
			}
		}
		for (int i = 0; i < _imageWriter.getHeight(); i += interval) {
			for (int j = 0; j < _imageWriter.getWidth(); j++) {
				_imageWriter.writePixel(j, i, 255 - _scene.get_background().getRed(),
						255 - _scene.get_background().getGreen(), 255 - _scene.get_background().getBlue());
			}
		}
	}
	/*
	 * /**
	 * 
	 * @param intersectionPoints
	 * 
	 * @return
	 * 
	 * private Map<Geometry,Point3D> getClosestPoint(Map<Geometry,List<Point3D>>
	 * intersectionPoints) { double distance = Double.MAX_VALUE; Point3D P0 =
	 * _scene.get_camera().getPO(); Point3D minDistancePoint = null;
	 * 
	 * for(Point3D p: intersectionPoints) { if(P0.distance(p) < distance) {
	 * minDistancePoint = new Point3D(p); distance = P0.distance(p); } }
	 * 
	 * return minDistancePoint; }
	 */

	/**
	 * @param intersectionPoints
	 * @return
	 */
	private GeoPoint getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
		// In the intersectionPoints - find the point with minimal distance from the ray
		// begin point and return it
		double distance = Double.MAX_VALUE;
		Point3D P0 = _scene.get_camera().getPO();

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
				Ray r = new Ray(_scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),
						i, j, _scene.get_screenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
				Map<Geometry, List<Point3D>> intersectionPoints = this.get_scene().get_geometries()
						.findIntersections(r);// TODO: fix
				if (intersectionPoints.isEmpty()) {
					_imageWriter.writePixel(i, j, _scene.get_background());
				} else {
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);// TODO: fix
					_imageWriter.writePixel(i, j, this.calcColor(closestPoint));
				}
			}
		}
	}

}

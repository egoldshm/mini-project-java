package renderer;

import java.util.*;
import java.util.Map.Entry;

import Elements.LightSource;

import java.awt.Color;

import Scene.Scene;
import primitives.*;
import primitives.Vector;
import Geometries.*;

/**
 * class that render picture from scene and camera
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
		Color ambientLight = _scene.getAmbientLight().getIntensity();
		Color emissionLight = geometry.getEmmission();
		Color difTemp, specTemp;
		if(this.get_scene().getLights().isEmpty())
			return Util.addColors(ambientLight, emissionLight);
		Iterator<LightSource> lights = this.get_scene().getLightsIterator();
		int rd=0, gd=0, bd=0, rs=0, gs=0, bs=0,count = 0;
		//summing the specular and diffusive component for every light source
		while (lights.hasNext()){
			count++;
			LightSource light = lights.next();
			difTemp = calcDiffusiveComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point));
			specTemp= calcSpecularComp(geometry.getMaterial().getKs(), new Vector(point.subtract(_scene.getCamera().getPO())), geometry.getNormal(point), light.getL(point), geometry.getMaterial().getnShininess(),light.getIntensity(point));
			rd+=difTemp.getRed();
			gd+=difTemp.getGreen();
			bd+=difTemp.getBlue();
			rs+=specTemp.getRed();
			gs+=specTemp.getGreen();
			bs+=specTemp.getBlue();
		}
		Color diffuseLight = new Color(rd/count, gd/count, bd/count);
		Color specularLight = new Color(rs/count, gs/count, bs/count);
		//summing ambient, emission, diffusive, specular light components
		return Util.addColors(ambientLight, emissionLight, diffuseLight, specularLight);
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
					Set<Entry<Geometry, Point3D>> it = closestPoint.entrySet();
					if(it.iterator().hasNext())
					{
					Entry<Geometry, Point3D> a = it.iterator().next();
					Geometry g = a.getKey();
					Point3D p = a.getValue();
					_imageWriter.writePixel(i, j, this.calcColor(g,p));
					}
					else
					{
						_imageWriter.writePixel(i, j, _scene.getBackground());
					}
				}
			}
		}
	}

	/**
	 * @param _kd
	 * @param _N
	 * @param _L
	 * @param _Il
	 * @return diffusive component of light
	 */
	private Color calcDiffusiveComp(double _kd, Vector _N, Vector _L, Color _Il)
	{
		return Util.brightness(_Il, _kd * _N.scalarMultiplication(_L));
	}
	
	/**
	 * @param _ks
	 * @param _V
	 * @param _Norm
	 * @param _L
	 * @param _n
	 * @param _Il
	 * @return specular component of light
	 */
	private Color calcSpecularComp(double _ks, Vector _V, Vector _Norm, Vector _L, int _n, Color _Il)
	{
		//we may need to add an absolute value here because of the way the normal is calculated
		Vector R = _L.subtractVector(_Norm.scalarMultiplication(2 * Math.abs(_L.scalarMultiplication(_Norm))));
		return Util.brightness(_Il, _ks * Math.pow(_V.scalarMultiplication(R), _n));
	}
	// ***************** Admin ********************** //

}

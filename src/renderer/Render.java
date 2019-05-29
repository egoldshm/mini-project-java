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
	 * @return Scene in the render
	 */
	public Scene get_scene() {
		return new Scene(this._scene);
	}
	
	/**
	 * @param Sence in the render
	 */
	public void set_scene(Scene _scene) {
		this._scene = _scene;
	}
	
	/**
	 * @return ImageWriter in the render
	 */
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	/**
	 * @param ImageWriter in the render
	 */
	public void set_imageWriter(ImageWriter _imageWriter) {
		this._imageWriter = _imageWriter;
	}

	// ***************** Operations ******************** //

	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
		while (geometries.hasNext()) {
			Geometry geometry = geometries.next();
			List<Point3D> geometryIntersectionPoints = new ArrayList<Point3D>(geometry.findIntersections(ray));
			if(!geometryIntersectionPoints.isEmpty())
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
		Color ambientLight = _scene.getAmbientLight().getIntensity(point);
		Color emissionLight = geometry.getEmmission();
		Color diffuseLight = new Color(0, 0, 0);
		Color specularLight = new Color(0, 0, 0);
		Color difTmp, specTmp;
		Iterator<LightSource> lights = this.get_scene().getLightsIterator();
		LightSource light;
		//summing the specular and diffusive component for every light source
		while (lights.hasNext()){
			light = lights.next();
			difTmp = addColors(diffuseLight, calcDiffusiveComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point)));
			diffuseLight = new Color(difTmp.getRGB());
			specTmp = addColors(specularLight, calcSpecularComp(geometry.getMaterial().getKs(), new Vector(point.subtract(_scene.getCamera().getPO())), geometry.getNormal(point), light.getL(point), geometry.getMaterial().getnShininess(),light.getIntensity(point)));
			specularLight= new Color(specTmp.getRGB());
		}
		//summing ambient, emission, diffusive, specular light components
		Color add1=addColors(ambientLight,emissionLight);
	    Color add2=addColors(diffuseLight,specularLight);
	    return addColors(add1,add2);
	}
	
    private Color addColors(Color a, Color b)
    {
        // summing every R,GB and dividing by 2
        int R=Math.min(a.getRed() +b.getRed(),255);
        int G=Math.min(a.getGreen() +b.getGreen(),255);
        int B=Math.min(a.getBlue() +b.getBlue(),255);
        Color I0 = new Color (R,G,B);//creating the new color
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
				if(i==340 && j==210)
				{
					int p;
					p=9;		
				}
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
		_N = _N.normalizationOfVector();
		_L = _L.normalizationOfVector();
		double tmp = Math.abs(_kd * (_N.scalarMultiplication(_L)));
		return new Color(SpecinCol(_Il.getRed(),tmp),SpecinCol(_Il.getGreen(),tmp),SpecinCol(_Il.getBlue(),tmp));
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
		
		_L = _L.normalizationOfVector();
		_Norm = _Norm.normalizationOfVector();
		_V = _V.normalizationOfVector();
		Vector tmp = new Vector(_Norm);
		Vector R = new Vector(_L);
		tmp = tmp.scalarMultiplication(-2 * _L.scalarMultiplication(_Norm));
		R = new Vector(R.addVector(tmp).normalizationOfVector());
		double multiColor=0;
        if(_V.scalarMultiplication(R)>0)
        	multiColor=_ks*(Math.pow((_V.scalarMultiplication(R)),_n));
		return new Color(SpecinCol(_Il.getRed(),multiColor),SpecinCol(_Il.getGreen(),multiColor),SpecinCol(_Il.getBlue(),multiColor));
	}
	
	private int SpecinCol( int Inten, double tmp)
    {
       int tmp2=(int)(Inten*tmp);
        if(tmp2>255)
            return 255;
        if(tmp2<0)
            return 0;
        return tmp2;
    }

	// ***************** Admin ********************** //

}

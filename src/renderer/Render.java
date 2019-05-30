package renderer;

import java.util.*;
import java.util.Map.Entry;
import java.awt.Color;

import elements.LightSource;
import geometries.*;
import primitives.*;
import primitives.Vector;
import scene.Scene;

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

	/**
	 * @param ray by which we look at the scene
	 * @return A map that connects cutting points and the geometry of those cut points.
	 */
	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		Iterator<Geometry> geometries = _scene.getGeometriesIterator();
		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
		//Moves to any shape in the scene and inserts the cut points with it to the MAPõ
		while (geometries.hasNext()) {
			Geometry geometry = geometries.next();
			List<Point3D> geometryIntersectionPoints = new ArrayList<Point3D>(geometry.findIntersections(ray));
			//If there are no cut points - do not insert.
			if(!geometryIntersectionPoints.isEmpty())
				intersectionPoints.put(geometry,geometryIntersectionPoints);
		}
		return intersectionPoints;
	}

	
	/**
	 * A function that calculates the color of a particular point in a scene.
	 * @param geometry on which the point is located 
	 * @param The point for which we calculate the color.
	 * @return Color at the point
	 */
	private Color calcColor(Geometry geometry, Point3D point) {
		Color ambientLight = _scene.getAmbientLight().getIntensity(point);//ambient
		Color emissionLight = geometry.getEmmission();//emission
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
	
    /**
     * A function that get two colors is summed together logically.
     * @param a first color
     * @param b second color
     * @return The result of the connection between the two colors
     */
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
	 * A function that draws grid at a certain interval in the image.
	 * @param interval between lines
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
	 * A function that accepts a map of shape and cut points, and returns for each shape the point closest to the point of view.
	 * @param intersectionPoints Map of cutting points with geometries
	 * @return A new map with geometry and the nearest point.
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

	/**
	 * A function that creates the scene given the geometries and shapes into an image file.
	 */
	public void renderImage() {
		//Passes each pixel in the image, and checks which color should be put, if it has a cut point - finds using the calColor function. If not - painting with the color of the background.
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				Ray r = new Ray(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),
						i, j, _scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
				Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(r);
				// if no intersection Points - put background color
				if (intersectionPoints.isEmpty())
				{
					_imageWriter.writePixel(i, j, _scene.getBackground());
				}
				else
					{
					//find the closest point
					Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
					Set<Entry<Geometry, Point3D>> it = closestPoint.entrySet();
					//check if point exist
					if(it.iterator().hasNext())
					{
						Entry<Geometry, Point3D> a = it.iterator().next();
						Geometry g = a.getKey();
						Point3D p = a.getValue();
						_imageWriter.writePixel(i, j, this.calcColor(g,p));
					}
					else
					{
						// if no intersection Points - put background color
						_imageWriter.writePixel(i, j, _scene.getBackground());
					}
				}
			}
		}
	}

	/**
	 * A function that calculates the diffusive for a point
	 * @param _kd The factor of diffusive in geometry
	 * @param _N Normal in the point
	 * @param _L vector that light is coming from
	 * @param _Il color of the light
	 * @return diffusive component of light
	 */
	private Color calcDiffusiveComp(double _kd, Vector _N, Vector _L, Color _Il)
	{
		_N = _N.normalizationOfVector();//N.normalize()
		_L = _L.normalizationOfVector();//L.normalize()
		double tmp = Math.abs(_kd * (_N.scalarMultiplication(_L)));//abs(kd * (N * L))
		return new Color(SpecinCol(_Il.getRed(),tmp),SpecinCol(_Il.getGreen(),tmp),SpecinCol(_Il.getBlue(),tmp));
		//returning the scaled color
	}
	
	/**
	 * @param _ks factor of the specular
	 * @param _V Vector between the point and the camera
	 * @param _Norm normal in the point
	 * @param _L vector where the light come from
	 * @param _n shininess factor
	 * @param _Il color at the point
	 * @return specular component of light
	 */
	private Color calcSpecularComp(double _ks, Vector _V, Vector _Norm, Vector _L, int _n, Color _Il)
	{
		
		_L = _L.normalizationOfVector();//L.normalize()
		_Norm = _Norm.normalizationOfVector();//Norm.normalize()
		_V = _V.normalizationOfVector();//V.normalize()
		Vector tmp = new Vector(_Norm);//copy of Norm
		Vector R = new Vector(_L);//copy of L
		tmp = tmp.scalarMultiplication(-2 * _L.scalarMultiplication(_Norm));//tmp = -2 * Norm * (L * Norm)
		R = new Vector(R.addVector(tmp).normalizationOfVector());//R = (R +tmp).normalize()
		double multiColor=0;
        if(_V.scalarMultiplication(R)>0)
        	multiColor=_ks*(Math.pow((_V.scalarMultiplication(R)),_n));//getting the scaling factor of the color including shininess
		return new Color(SpecinCol(_Il.getRed(),multiColor),SpecinCol(_Il.getGreen(),multiColor),SpecinCol(_Il.getBlue(),multiColor));
		//returning the scaled color
	}
	
	/**
	 * Auxiliary function multiply number by intensity (for light)
	 */
	private int SpecinCol( int Inten, double tmp)
    {
		//making sure Inten*tmp is bounded by 0 and 255
       int t=(int)(Inten*tmp);
        if(t>255)
            return 255;
        if(t<0)
            return 0;
        return t;
    }

	// ***************** Admin ********************** //

}

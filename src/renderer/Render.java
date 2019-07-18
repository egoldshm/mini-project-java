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
	private final int RECURSION_LEVEL = 2;

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
	 * @param inRay ray of sight from previous source (camera, reflection or refraction) to point
	 * @param level level of recursion
	 * @return Color at the point
	 */
	
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level)
	{
		if(level == this.RECURSION_LEVEL)
		{
			return new Color(0, 0, 0);
		}
		Color ambientLight = _scene.getAmbientLight().getIntensity(point);//ambient
		Color emissionLight = geometry.getEmmission();//emission
		Color diffuseLight = new Color(0, 0, 0);
		Color specularLight = new Color(0, 0, 0);
		Color difTmp = new Color(0, 0, 0), specTmp= new Color(0, 0, 0);
		Iterator<LightSource> lights = this.get_scene().getLightsIterator();
		LightSource light;
		//summing the specular and diffusive component for every light source
		while (lights.hasNext()){
			light = lights.next();
			Vector l = light.getL(point);
			Vector v = point.subtract(_scene.getCamera().getPO()).normalizationOfVector();
	        Vector n = geometry.getNormal(point);
			if(!this.occluded(light, point, geometry) && n.scalarMultiplication(l) * n.scalarMultiplication(v) > 0)
			{
				difTmp = addColors(diffuseLight, calcDiffusiveComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point)));
				diffuseLight = new Color(difTmp.getRGB());
				specTmp = addColors(specularLight, calcSpecularComp(geometry.getMaterial().getKs(), new Vector(point.subtract(_scene.getCamera().getPO())), geometry.getNormal(point), light.getL(point), geometry.getMaterial().getnShininess(),light.getIntensity(point)));
				specularLight= new Color(specTmp.getRGB());
			}
			else
			{
				 diffuseLight = new Color(0, 0, 0);
	             specularLight = new Color(0, 0, 0);
			}
			
		}
		
		//reflection calculation
		Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
		Map<Geometry, Point3D> reflectedEntry = this.getClosestPoint(this.getSceneRayIntersections(reflectedRay));
		Color reflectedColor;
		if(!reflectedEntry.isEmpty())
		{
			Entry<Geometry, Point3D> a = reflectedEntry.entrySet().iterator().next();
			Geometry g = a.getKey();
			Point3D p = a.getValue();
			reflectedColor = calcColor(g, p, reflectedRay, level + 1);
		}
		else {
			reflectedColor = new Color(0,0,0);
		}
		
		double Kr = geometry.getMaterial().getKr();
		Color reflectedLight = new Color(SpecinCol(reflectedColor.getRed(), Kr), SpecinCol(reflectedColor.getGreen(), Kr), SpecinCol(reflectedColor.getBlue(), Kr));
		
		//refraction calculation
		Ray refractedRay = new Ray(constructRefractedRay(geometry, point, inRay));
		Map<Geometry, Point3D> refractedEntry = this.getClosestPoint(this.getSceneRayIntersections(refractedRay));
		Set<Entry<Geometry, Point3D>> it2 = refractedEntry.entrySet();
		Color refractedColor;
		if(it2.iterator().hasNext())
		{
			Entry<Geometry, Point3D> a = it2.iterator().next();
			Geometry g = a.getKey();
			Point3D p = a.getValue();
			refractedColor = new Color(calcColor(g, p, refractedRay, level + 1).getRGB());
		}
		else {
			refractedColor = new Color(0,0,0);
		}
		
		double Kt = geometry.getMaterial().getKt();
		Color refractedLight = new Color(SpecinCol(refractedColor.getRed(), Kt), SpecinCol(refractedColor.getGreen(), Kt), SpecinCol(refractedColor.getBlue(), Kt));
		
		
		//summing ambient, emission, diffusive, specular light components
		Color add1=new Color(addColors(ambientLight,emissionLight).getRGB());
        Color add2=new Color(addColors(diffuseLight,specularLight).getRGB());
        Color add3=new Color(addColors(refractedLight,reflectedLight).getRGB());
        Color add4=new Color(addColors(add1,add2).getRGB());
        return  new Color(addColors(add3,add4).getRGB());
	}
	
	
	 /**
	  * function for the construct ray for Reflected
	 * @param normal vector of the geomery in the point
	 * @param point the point where we calc the Reflected
	 * @param inRay ray that we get
	 * @return Ray new ray we get
	 */
	private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay)
	    {
	     Vector D=(new Vector(inRay.getDirection())).normalizationOfVector();//creating the direction Vector
	     Vector epsVector = new Vector(normal.normalizationOfVector());//a copy for the normal vector
	     epsVector = epsVector.scalarMultiplication(2);
	     point = point.add(epsVector);
	     Ray R=new Ray(point,D);
	     Vector tmp=new Vector(normal);
	     tmp = tmp.scalarMultiplication(-2*D.scalarMultiplication(normal));
	     R.setDirection(R.getDirection().addVector(tmp).normalizationOfVector());
	     return R;

	    }
	   
	 
	    /**
	     * function for the construct ray for Refracted
	     * @param geometry geomery where we calc the ray
	     * @param point point where we calc the ray
	     * @param  inRay ray that we get
	     * @return Ray new ray we get
	     */
	    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay)
	    {
	        Vector Normal = geometry.getNormal(point);
	        Normal = Normal.scalarMultiplication(-2);
	        point = point.add(Normal);
	        Vector no = geometry.getNormal(point);
	        no = no.normalizationOfVector();
	        Vector epsVector = new Vector(no);
	        epsVector = epsVector.scalarMultiplication(2).normalizationOfVector();
	        Point3D pointResult = new Point3D(point.getX().subtract(epsVector.getHead().getX()),
	        		point.getY().subtract(epsVector.getHead().getY()),point.getZ().subtract(epsVector.getHead().getZ()));
	        return new Ray (pointResult,inRay.getDirection());
	    }
	
	/**
	 * A function that calculates the color of a particular point in a scene that calls the recursive function.
	 * @param geometry on which the point is located 
	 * @param The point for which we calculate the color.
	 * @param inRay ray of sight from previous source (camera, reflection or refraction) to point
	 * @return Color at the point
	 */
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay)
	{
		return calcColor(geometry, point, inRay, 0);
	}
	
	/**
	 * this function decide if a point is cover by other geometries
	 * @param light the source light
	 * @param point the point from where we send  the light check shadow
	 * @param geometry the point on the geometry
	 * @return if the geometry occluded or not.
	 */
	private boolean occluded(LightSource light, Point3D point, Geometry geometry)
	{
		
		Vector lightDirection = light.getL(point).scalarMultiplication(-1).normalizationOfVector();
		
		Vector epsVector = new Vector(geometry.getNormal(point).scalarMultiplication(2));
		
		Point3D geometryPoint = new Point3D(point).add(epsVector);
		
		Ray lightRay = new Ray(geometryPoint, lightDirection);
		
		Map<Geometry, List<Point3D>> intersectionPoints = this.getSceneRayIntersections(lightRay);
		
		//Flat geometry cannot intersect itself
		if(geometry instanceof FlatGeometry)
		{
			intersectionPoints.remove(geometry);
		}
		
		for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
		if(entry.getKey().getMaterial().getKt() == 0)
		{
			return true;
		}
		return false;
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
		int idodo;
		//Passes each pixel in the image, and checks which color should be put, if it has a cut point - finds using the calColor function. If not - painting with the color of the background.
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				if (i== 557 && j == 256)
				{
					idodo=0;
					
				}
				if (i== 590 && j == 262)
				{
					idodo=0;
					
					
		
				}
				List<Ray> rays = new ArrayList<Ray>(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),
						i, j, _scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
				List<Color> listColor = new ArrayList<Color>();
				for(Ray r : rays)
				{
					Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(r);
					// if no intersection Points - put background color
					if (intersectionPoints.isEmpty())
					{
						listColor.add(_scene.getBackground());
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
							listColor.add(this.calcColor(g,p, r));
						}
						else
						{
							// if no intersection Points - put background color
							listColor.add(_scene.getBackground());
						}
					
				}
				
				}
				int r=0,g=0,b=0;
				for(Color c : listColor)
				{
					r+=c.getRed();
					g+=c.getGreen();
					b+=c.getBlue();

				}
				this.get_imageWriter().writePixel(i, j,this.addColors(new Color(0,0,0), new Color(r/rays.size(), g/rays.size(), b/rays.size())));
				
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
	
		_L = _L.normalizationOfVector();
		_Norm = _Norm.normalizationOfVector();
		_V = _V.normalizationOfVector();
	
		Vector tmp = new Vector(_Norm);
		Vector R = new Vector(_L);
		tmp = tmp.scalarMultiplication(-2 * _L.scalarMultiplication(_Norm));//tmp = -2 * Norm * (L * Norm)
		R = new Vector(R.addVector(tmp).normalizationOfVector());//R = (R +tmp).normalizationOfVector()
		double multiColor=0;
        if(_V.scalarMultiplication(R)>0 )
        	multiColor=_ks*(Math.pow((_V.scalarMultiplication(R)),_n));//getting the scaling factor of the color including shininess
		if (multiColor>0)multiColor*=0;
		
        return new Color(SpecinCol(_Il.getRed(),multiColor),SpecinCol(_Il.getGreen(),multiColor),SpecinCol(_Il.getBlue(),multiColor));
	}
	/*private Color calcSpecularComp(double ks,Vector V,Vector normal,Vector L,double shine,Color Il)
	//calculate Specular 
	{
		Vector V1=V.normalize();
		Vector normal1=normal.normalize();
		Vector L1= L.normalize();
		double dot = (L1.dotProduct(normal1))*2;
		//	if(dot>0)dot=0;
		Vector R = L1.substrct(normal1.scale(dot)).normalize();
		double temp = ks * Math.pow(V1.normalize().dotProduct(R), shine);
		double scelar = Math.abs(temp);
		return ColorScale(Il,scelar);
	}
	 */
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

/**
 * 
 */
package scene;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;

import elements.*;
import geometries.*;
/**
 * @author eitan
 * class represents Scene
 */
public class Scene {
	private String _sceneName;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries geometries;
	private List<LightSource> lights;
	private Camera _camera;
	private double _screenDistance;
	
	// ***************** Constructors ********************** // 
	
	/**
	 * Default Constructor
	 */
	public Scene()
	{
		this._sceneName = "";
		this._background = new Color(255,255,255);
		this._ambientLight = new AmbientLight();
		this.geometries = new Geometries();
		this._camera = new Camera();
		this._screenDistance = 100;
		this.lights = new ArrayList<LightSource>();
	}
	
	/**
	 * Constructor with parameters
	 * @param sceneName
	 * @param background color of the scene
	 * @param ambientLight of the scene
	 * @param geometries in the scene
	 * @param camera in the scene, when we look from
	 * @param screenDistance
	 */
	public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, Geometries _geometries,
			Camera _camera, double _screenDistance) {
		this._sceneName = _sceneName;
		this._background = _background;
		this._ambientLight = _ambientLight;
		this.geometries = _geometries;
		this._camera = _camera;
		this._screenDistance = _screenDistance;
		this.lights = new ArrayList<LightSource>();
	}
	/**
	 * Constructor with parameters
	 * @param sceneName
	 * @param background color of the scene
	 * @param ambientLight of the scene
	 * @param geometries in the scene
	 * @param camera in the scene, when we look from
	 * @param screenDistance
	 * @param lights in the scene
	 */
	public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, Geometries _geometries, Camera _camera, double _screenDistance, List<LightSource> _lights) {
		this._sceneName = _sceneName;
		this._background = _background;
		this._ambientLight = _ambientLight;
		this.geometries = _geometries;
		this._camera = _camera;
		this._screenDistance = _screenDistance;
		this.lights = _lights;
	}
	
	/**
	 * copy constructor with
	 * @param scene
	 */
	public Scene(Scene scene) {
		this._sceneName = scene._sceneName;
		this._background = scene._background;
		this._ambientLight = scene._ambientLight;
		this.geometries = scene.geometries;
		this._camera = scene._camera;
		this._screenDistance = scene._screenDistance;
		this.lights = scene.lights;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * @return the sceneName
	 */
	public String getSceneName() {
		return _sceneName;
	}
	
	/**
	 * @param sceneName the name to set
	 */
	public void setSceneName(String _sceneName) {
		this._sceneName = _sceneName;
	}
	
	/**
	 * @return the background color
	 */
	public Color getBackground() {
		return _background;
	}
	
	/**
	 * @param background the background color
	 */
	public void setBackground(Color _background) {
		this._background = _background;
	}
	
	/**
	 * @return the ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	
	/**
	 * @param ambientLight the ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}
	
	/**
	 * @return the geometries in the scene
	 */
	public Geometries getGeometries() {
		return geometries;
	}
	
	/**
	 * @param _geometries the _geometries to set
	 */
	public void setGeometries(Geometries _geometries) {
		this.geometries = _geometries;
	}
	
	/**
	 * @return the lights in the scene
	 */
	public List<LightSource> getLights() {
		return lights;
	}

	/**
	 * @param lights the lights to set
	 */
	public void setLights(List<LightSource> lights) {
		this.lights = lights;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return _camera;
	}
	
	/**
	 * @param camera the camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}
	
	/**
	 * @return the screenDistance
	 */
	public double getScreenDistance() {
		return _screenDistance;
	}
	
	/**
	 * @param screenDistance the screenDistance to set
	 */
	public void setScreenDistance(double _screenDistance) {
		this._screenDistance = _screenDistance;
	}
	
	// ***************** Operations ******************** // 
	
	/**
	 * @return iterator of geometries
	 */
	public Iterator<Geometry> getGeometriesIterator(){
		return geometries.getGeometries().iterator();
	}
	
	/**
	 * @return iterator of light sources
	 */
	public Iterator<LightSource> getLightsIterator()
	{
		return lights.iterator();
	}

	// ***************** Admin ********************** //
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//self check
		if (this == obj)
				return true;
			// null check
		if (obj == null)
			    return false;
		// type check and cast
		if (getClass() != obj.getClass())
			   	return false;
		Scene se = (Scene)obj;
		return (this._sceneName.equals(se._sceneName) && _background.equals(se._background) &&
				_ambientLight.equals(se._ambientLight) &&
				geometries.equals(se.geometries) && _camera.equals(se._camera)&& _screenDistance == se._screenDistance);


	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this._sceneName +" "+ this._screenDistance + this.geometries + " " + this.lights + " " + this._ambientLight + " " + this._background + " " + this._camera;
	}
}

/**
 * 
 */
package Scene;
import java.util.Iterator;

import java.awt.Color;

import Elements.*;
import Geometries.*;
/**
 * @author eitan
 *
 */
public class Scene {
	/**
	 * Default Constructor
	 */
	public Scene()
	{
		
	}
	/**
	 * Constructor with parameters
	 * @param _sceneName
	 * @param _background
	 * @param _ambientLight
	 * @param _geometries
	 * @param _camera
	 * @param _screenDistance
	 */
	public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, Geometries _geometries,
			Camera _camera, double _screenDistance) {
		this._sceneName = _sceneName;
		this._background = _background;
		this._ambientLight = _ambientLight;
		this.geometries = _geometries;
		this._camera = _camera;
		this._screenDistance = _screenDistance;
	}
	
	/**
	 * copy ctor with
	 * @param scene
	 */
	public Scene(Scene scene) {
		this._sceneName = scene._sceneName;
		this._background = scene._background;
		this._ambientLight = scene._ambientLight;
		this.geometries = scene.geometries;
		this._camera = scene._camera;
		this._screenDistance = scene._screenDistance;
	}

	private String _sceneName;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries geometries;
	private Camera _camera;
	private double _screenDistance;
	/**
	 * @return the _sceneName
	 */
	public String getSceneName() {
		return _sceneName;
	}
	/**
	 * @param _sceneName the _sceneName to set
	 */
	public void setSceneName(String _sceneName) {
		this._sceneName = _sceneName;
	}
	/**
	 * @return the _background
	 */
	public Color getBackground() {
		return _background;
	}
	/**
	 * @param _background the _background to set
	 */
	public void setBackground(Color _background) {
		this._background = _background;
	}
	/**
	 * @return the _ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}
	/**
	 * @return the _geometries
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
	 * @return the _camera
	 */
	public Camera getCamera() {
		return _camera;
	}
	/**
	 * @param _camera the _camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * @return the _screenDistance
	 */
	public double getScreenDistance() {
		return _screenDistance;
	}
	/**
	 * @param _screenDistance the _screenDistance to set
	 */
	public void setScreenDistance(double _screenDistance) {
		this._screenDistance = _screenDistance;
	}
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
	public Iterator<Intersectable> getGeometriesIterator(){
		return geometries.getGeometries().iterator();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

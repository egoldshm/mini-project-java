/**
 * 
 */
package Scene;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.sun.prism.paint.Color;

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
	public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, List<Geometry> _geometries,
			Camera _camera, double _screenDistance) {
		this._sceneName = _sceneName;
		this._background = _background;
		this._ambientLight = _ambientLight;
		this._geometries = _geometries;
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
		this._geometries = scene._geometries;
		this._camera = scene._camera;
		this._screenDistance = scene._screenDistance;
	}

	private String _sceneName;
	private Color _background;
	private AmbientLight _ambientLight;
	private List<Geometry> _geometries;
	private Camera _camera;
	private double _screenDistance;
	/**
	 * @return the _sceneName
	 */
	public String get_sceneName() {
		return _sceneName;
	}
	/**
	 * @param _sceneName the _sceneName to set
	 */
	public void set_sceneName(String _sceneName) {
		this._sceneName = _sceneName;
	}
	/**
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}
	/**
	 * @param _background the _background to set
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}
	/**
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
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
	public List<Geometry> get_geometries() {
		return _geometries;
	}
	/**
	 * @param _geometries the _geometries to set
	 */
	public void set_geometries(List<Geometry> _geometries) {
		this._geometries = _geometries;
	}
	/**
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}
	/**
	 * @param _camera the _camera to set
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * @return the _screenDistance
	 */
	public double get_screenDistance() {
		return _screenDistance;
	}
	/**
	 * @param _screenDistance the _screenDistance to set
	 */
	public void set_screenDistance(double _screenDistance) {
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
				_geometries.equals(se._geometries) && _camera.equals(se._camera)&& _screenDistance == se._screenDistance);


	}
<<<<<<< HEAD
	public Iterator<Geometry> getGeometriesIterator(){
=======
	public Iterator <Geometry> getGeometriesIterator(){
>>>>>>> branch 'master' of https://github.com/egoldshm/mini-project-java
		return _geometries.iterator();
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

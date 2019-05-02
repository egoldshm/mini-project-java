package renderer;

import Scene.Scene;

public class Render {
	
	/**
	 * default constructor
	 */
	public Render() {
	
	}
	
	
	/**
	 * constructor with params
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
	
	Scene _scene ; 
	ImageWriter _imageWriter ;
	
	
	
	
	

}

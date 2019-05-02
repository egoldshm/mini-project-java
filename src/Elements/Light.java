/**
 * 
 */
package Elements;

import java.awt.Color;

/**
 * @author eitan
 * abstract class for light in sense 
 */
public abstract class Light {
	
	private Color _color;
	
	// ***************** Constructors ********************** // 
	/**
	 * @param _color color of the sence
	 */
	public Light() {
		_color = null;
	}
	
	/**
	 * @param color of the sense
	 */
	public Light(Color _color) {
		this._color = _color;
	}
	// ***************** Getters/Setters ********************** //
	/**
	 * @return the _color
	 */
	public Color getColor() {
		return _color;
	}
	/**
	 * @param _color the _color to set
	 */
	public void setColor(Color _color) {
		this._color = _color;
	}
	// ***************** Operations ******************** // 
	/**
	 * @return color in point
	 */
	abstract Color getIntensity();
}

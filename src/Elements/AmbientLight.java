/**
 * 
 */
package Elements;

import com.sun.prism.paint.Color;

/**
 * @author eitan
 * Class for ambient light
 */
public class AmbientLight {
	
	/** ctor with 2 param
	 * @param _color
	 * @param ka
	 */
	public AmbientLight(Color _color, double ka) {
		super();
		this._color = _color;
		Ka = ka;
	}
	/**
	 * default ctor
	 */
	public AmbientLight() {
		this._color = null;
		Ka = 0;
	}
	/**
	 * copy ctor
	 */
	public AmbientLight(AmbientLight ambientLight) {
		this._color = ambientLight._color;
		Ka = ambientLight.Ka;
	}
	
//TODO: to check if the class color it the right one
private Color _color;
private double Ka;
/**
 * @param color of the light
 */
public void setColor(Color color)
{
	_color = color;
}
/**
 * @return color of the light
 */
public Color getColor() {
	return _color;
}
/**
 * @param ka Light attenuation coefficient
 */
public void setKa(double ka)
{
	this.Ka = ka;
}
/**
 * @return ka Light attenuation coefficient
 */
public double getKa()
{
	return Ka;
}
public Color getIntensity()
{
	return null;
}
}

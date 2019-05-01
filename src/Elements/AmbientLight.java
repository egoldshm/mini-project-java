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
	//TODO: to check if the class color it the right one
private Color _color;
private double Ka;
public void setColor(Color color)
{
	_color = color;
}
public Color getColor() {
	return _color;
}
public void setKa(double ka)
{
	this.Ka = ka;
}
public double getKa(double ka)
{
	return ka;
}
public Color getIntensity()
{
	return null;
}
}

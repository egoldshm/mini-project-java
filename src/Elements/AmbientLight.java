/**
 * 
 */
package Elements;

import java.awt.Color;

/**
 * @author eitan Class for ambient light
 */
public class AmbientLight extends Light {

	private double Ka;
	
	// ***************** Constructors ********************** // 

	/**
	 * ctor with 2 param
	 * 
	 * @param _color
	 * @param ka
	 */
	public AmbientLight(Color _color, double ka) {
		super(_color);
		Ka = ka;
	}

	/**
	 * default ctor
	 */
	public AmbientLight() {
		super();
		Ka = 0;
	}

	/**
	 * copy ctor
	 */
	public AmbientLight(AmbientLight ambientLight) {
		super(ambientLight.getColor());
		Ka = ambientLight.Ka;
	}
	
	
	// ***************** Getters/Setters ********************** //
	/**
	 * @param ka Light attenuation coefficient
	 */
	public void setKa(double ka) {
		this.Ka = ka;
	}

	/**
	 * @return ka Light attenuation coefficient
	 */
	public double getKa() {
		return Ka;
	}

	// ***************** Operations ******************** // 
	public Color getIntensity() {
		double b = getKa() * getColor().getBlue();
		double r = getKa() * getColor().getRed();
		double g = getKa() * getColor().getGreen();
		return new Color((int) b, (int) r, (int) g);
	}
}

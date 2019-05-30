/**
 * 
 */
package elements;

import java.awt.Color;

import primitives.Point3D;

/**
 * @author eitan
 * Class for ambient light
 */
public class AmbientLight extends Light {

	private double Ka;
	
	// ***************** Constructors ********************** // 

	/**
	 * Constructor
	 * @param color of the light
	 * @param ka Ambient factor
	 */
	public AmbientLight(Color _color, double ka) {
		super(_color);
		Ka = ka;
	}

	/**
	 * default constructor
	 */
	public AmbientLight() {
		super();
		Ka = 1;
	}

	/**
	 * copy constructor
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
	/* (non-Javadoc)
	 * @see Elements.Light#getIntensity(primitives.Point3D)
	 */
	public Color getIntensity(Point3D p) {
		double b = getKa() * getColor().getBlue();
		double r = getKa() * getColor().getRed();
		double g = getKa() * getColor().getGreen();
		return new Color((int) r, (int) g, (int) b);
	}
}

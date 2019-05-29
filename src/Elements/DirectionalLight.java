/**
 * 
 */
package Elements;

import java.awt.Color;
import primitives.*;

/**
 * @author eitan
 * Class of Direct Light Representation
 */
public class DirectionalLight extends Light implements LightSource{

	private Vector direction;

	// ***************** Constructors ********************** // 
	/**
	 * constructor
	 * @param direction Vector of Direction of light
	 * @param color of the light
	 */
	public DirectionalLight(Vector direction, Color color) {
		super(color);
		this.direction = direction;
	}

	/**
	 * Copy constructor
	 */
	public DirectionalLight(DirectionalLight directionalLight) {
		super(directionalLight.getColor());
		this.direction = directionalLight.direction;
	}

	/**
	 * Default constructor
	 */
	public DirectionalLight() {
		
	}

	
	// ***************** Getters/Setters ********************** //

	/**
	 * @return vector of the direction
	 */
	public Vector getDirection() {
		return direction;
	}

	/**
	 * @param vector of the direction
	 */
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
	// ***************** Operations ******************** // 

	
	/* (non-Javadoc)
	 * @see Elements.Light#getIntensity()
	 */
	@Override
	public Color getIntensity(Point3D point) {
		//The function just return the original intensity
		//IL = I0
		return this.getColor();
	}

	/* (non-Javadoc)
	 * @see Elements.LightSource#getL(primitives.Point3D)
	 */
	@Override
	public Vector getL(Point3D point) {
		return this.direction.normalizationOfVector();
	}




}

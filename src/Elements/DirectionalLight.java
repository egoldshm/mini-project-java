/**
 * 
 */
package Elements;

import java.awt.Color;
import primitives.*;

/**
 * @author eitan
 *
 */
public class DirectionalLight extends Light implements LightSource{

	private Vector direction;

	// ***************** Constructors ********************** // 
	/**
	 * @param direction
	 * @param color
	 */
	public DirectionalLight(Vector direction, Color color) {
		super(color);
		this.direction = direction;
	}

	public DirectionalLight(DirectionalLight directionalLight) {
		super(directionalLight.getColor());
		this.direction = directionalLight.direction;
	}

	/**
	 * 
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
	Color getIntensity() {
		// TODO Auto-generated method stub
		return this.getColor();
	}

	@Override
	public Color getIntensity(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getD(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}





}

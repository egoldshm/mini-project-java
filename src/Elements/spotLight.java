package Elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class spotLight extends pointLight {

	private Vector direction;

	// ***************** Constructors ********************** // 

	/**
	 * @param _color
	 * @param position
	 * @param kc
	 * @param kl
	 * @param kq
	 * @param direction
	 */
	public spotLight(Color _color, Point3D position, double kc, double kl, double kq, Vector direction) {
		super(_color, position, kc, kl, kq);
		this.direction = direction;
	}

	public spotLight(spotLight spotLight) {
		super(spotLight.getColor(), spotLight.getPosition(), spotLight.getKc(), spotLight.getKl(), spotLight.getKq());
		this.direction = spotLight.direction;
	}
	public spotLight(Color _color, Point3D position, double kc, double kl, double kq) {
		super(_color, position, kc, kl, kq);
	}

	public spotLight(pointLight pointLight) {
		super(pointLight);
	}

	public spotLight() {
	}

	
	// ***************** Getters/Setters ********************** //

	/**
	 * @return the direction
	 */
	public Vector getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Vector direction) {
		this.direction = direction;
	}

}

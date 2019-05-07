/**
 * 
 */
package Elements;

import java.awt.Color;

import primitives.Point3D;

/**
 * @author eitan
 *
 */
public class pointLight extends Light {

	private Point3D position;
	private double Kc, Kl, Kq;

	// ***************** Constructors ********************** // 

	/**
	 * @param _color
	 * @param position
	 * @param kc
	 * @param kl
	 * @param kq
	 */
	public pointLight(Color _color, Point3D position, double kc, double kl, double kq) {
		super(_color);
		this.position = position;
		Kc = kc;
		Kl = kl;
		Kq = kq;
	}

	/**
	 * @param copy ctor
	 */
	public pointLight(pointLight pointLight) {
		super(pointLight.getColor());
		this.position = pointLight.position;
		Kc = pointLight.Kc;
		Kl = pointLight.Kl;
		Kq = pointLight.Kq;
	}
	/**
	 * 
	 */
	public pointLight() {
	}
	
	// ***************** Getters/Setters ********************** //
	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}



	/**
	 * @param position the position to set
	 */
	public void setPosition(Point3D position) {
		this.position = position;
	}



	/**
	 * @return the kc
	 */
	public double getKc() {
		return Kc;
	}



	/**
	 * @param kc the kc to set
	 */
	public void setKc(double kc) {
		Kc = kc;
	}



	/**
	 * @return the kl
	 */
	public double getKl() {
		return Kl;
	}



	/**
	 * @param kl the kl to set
	 */
	public void setKl(double kl) {
		Kl = kl;
	}



	/**
	 * @return the kq
	 */
	public double getKq() {
		return Kq;
	}



	/**
	 * @param kq the kq to set
	 */
	public void setKq(double kq) {
		Kq = kq;
	}
	
	// ***************** Operations ******************** // 
	/* (non-Javadoc)
	 * @see Elements.Light#getIntensity()
	 */
	@Override
	public Color getIntensity() {
		return null;//TODO: fix
	}





}

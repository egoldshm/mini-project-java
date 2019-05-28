package Elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Util;
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
		this.direction = direction.normalizationOfVector();
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
		this.direction = direction.normalizationOfVector();
		
	}
	@Override
	public Color getIntensity(Point3D point)
	{
		//the function returns the base function's intensity and multiplies it by the projection of the direction onto the vector between the light and the point
		//IL = I0 * (D * L) / (Kc * Kl*d * Kq*d*d)
		 double d=this.getPosition().distance(point);
	     double t1= this.getKc()+this.getKl()*d+this.getKq()*d*d;
	     double t2=direction.scalarMultiplication(this.getL(point));
	     return new Color(CacInColor(this.getColor().getRed(),t2,t1),CacInColor(this.getColor().getGreen(),t2,t1),CacInColor(this.getColor().getBlue(),t2,t1));
		
	}
	private int CacInColor(int C,double tmp1, double tmp)
	{
	        int tmp2=(int)((C*tmp1)/tmp);
	        if(tmp2>255)
	            return 255;
	        if(tmp2<0)
	            return 0;
	        return tmp2;
	}

}

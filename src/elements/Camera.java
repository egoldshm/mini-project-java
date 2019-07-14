package elements;

import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * class to represent a camera in 3D space
 */
public class Camera {

	private Point3D _PO;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;

	// ***************** Constructors ********************** //

	/**
	 * Constructor with parameters
	 * 
	 * @param PO     point, where the camera is positioned in space
	 * @param vUp    Vector direction the top of the camera
	 * @param vTo    Direct vector direction of the camera
	 * @param vRight Vector orientation to the sides of the camera
	 */
	public Camera(Point3D PO, Vector vUp, Vector vTo, Vector vRight) {
		_PO = PO;
		_vUp = vUp;
		_vTo = vTo;
		_vRight = vRight;
	}

	/**
	 * @param PO  point, where the camera is positioned in space
	 * @param vTo Direct vector direction of the camera
	 * @param vUp Vector direction the top of the camera
	 */
	public Camera(Point3D PO, Vector vTo, Vector vUp) {
		_PO = PO;
		_vUp = vUp;
		_vTo = vTo;
		_vRight = vTo.CrossProductVector(vUp);
	}

	/**
	 * copy constructor
	 */
	public Camera(Camera c) {

		_PO = c.getPO();
		_vUp = c.getVUp();
		_vTo = c.getVTo();
		_vRight = c.getVRight();
	}

	/**
	 * Default constructor
	 */
	public Camera() {

	}

	// ***************** Getters/Setters ********************** //

	/**
	 * @return PO point, where the camera is positioned in space
	 */
	public Point3D getPO() {
		return new Point3D(_PO);
	}

	/**
	 * @param p PO point, where the camera is positioned in space
	 */
	public void setPO(Point3D p) {
		_PO = p;
	}

	/**
	 * @return Vector direction the top of the camera
	 */
	public Vector getVUp() {
		return _vUp;
	}

	/**
	 * @param v Vector direction the top of the camera
	 */
	public void setVUp(Vector v) {
		_vUp = v;
	}

	/**
	 * @return Direct vector direction of the camera
	 */
	public Vector getVTo() {
		return _vTo;
	}

	/**
	 * @param v Direct vector direction of the camera
	 */
	public void setVTo(Vector v) {
		_vTo = v;
	}

	/**
	 * @return Vector orientation to the sides of the camera
	 */
	public Vector getVRight() {
		return _vRight;
	}

	/**
	 * @param v Vector orientation to the sides of the camera
	 */
	public void setVRight(Vector v) {
		_vRight = v;
	}

	// ***************** Operations ******************** //

	/**
	 * Returns the Ray from the camera passing through a certain point
	 * 
	 * @param Nx
	 * @param Ny
	 * @param x
	 * @param y
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 */
	public Ray constructRayThroughPixelSingle(int Nx, int Ny, double x, double y, double screenDistance, double screenWidth,
			double screenHeight) {
		Point3D Pc = _PO.add(_vTo.scalarMultiplication(screenDistance));
		_vRight = _vTo.CrossProductVector(_vUp);
		double Rx = (screenWidth / Nx);
		double Ry = (screenHeight / Ny);
		Point3D P;
		P = Pc.add(_vRight.scalarMultiplication((x - (Nx / 2.0d)) * Rx - (Rx / 2.0d))
				.subtractVector(_vUp.scalarMultiplication(Ry * (y - Ny / 2.0d) - Ry / 2.0d)));
		
		return new Ray(_PO, P.subtract(_PO).normalizationOfVector());
	}
	
	public List<Ray> constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDist, double screenWidth, double screenHeight)
	//constructRayThroughPixel
	{
		List<Ray> list= new ArrayList<Ray>();
		//find the width and height for one pixel
		double Rx= screenWidth/Nx;
		double Ry= screenHeight/Ny;
		
		Vector vto=this.getVTo().normalizationOfVector().scalarMultiplication(screenDist);
		//find the vector vTo
		Point3D pc=this.getPO().add(vto);
		//find the Point3D in the middle of view plane 
		
		//calculate the vector that we search 
		double nx=(double)Nx/2;
		double ny=(double)Ny/2;
		double dright= (x-nx)*Rx+(Rx/2.0d);
		double dup= (y-ny)*Ry+(Ry/2.0d);
		Vector vright= this.getVRight().scalarMultiplication(dright);;
		Vector vup=this.getVUp().scalarMultiplication(dup);
		Point3D pc2=pc.add(vright);
		
		Point3D RU=pc2.subtract(vup.getHead()).getHead();
		Vector vend= new Vector(new Point3D(RU)).normalizationOfVector();
		Ray r1=new Ray(RU,vend );
		r1.setDirection(r1.getDirection().normalizationOfVector());
		
		
		Point3D p1= new Point3D(RU.getX().subtract(new Coordinate(Rx/2)),RU.getY().add(new Coordinate(Ry/2)),RU.getZ());
		Vector vp1= new Vector(new Point3D(p1)).normalizationOfVector();
		Ray r2=new Ray(p1,vp1 );
		r2.setDirection(r2.getDirection().normalizationOfVector());
		
		Point3D p2= new Point3D(RU.getX().add(new Coordinate(Rx/2)),RU.getY().add(new Coordinate(Ry/2)),RU.getZ());
		Vector vp2= new Vector(new Point3D(p2)).normalizationOfVector();
		Ray r3=new Ray(p2,vp2 );
		r3.setDirection(r3.getDirection().normalizationOfVector());
		
		Point3D p3= new Point3D(RU.getX().add(new Coordinate(Rx/2)),RU.getY().subtract(new Coordinate(Ry/2)),RU.getZ());
		Vector vp3= new Vector(new Point3D(p3)).normalizationOfVector();
		Ray r4=new Ray(p3,vp3 );
		r4.setDirection(r4.getDirection().normalizationOfVector());
		
		Point3D p4= new Point3D(RU.getX().subtract(new Coordinate(Rx/2)),RU.getY().subtract(new Coordinate(Ry/2)),RU.getZ());
		Vector vp4= new Vector(new Point3D(p4)).normalizationOfVector();
		Ray r5=new Ray(p4,vp4);
		r5.setDirection(r5.getDirection().normalizationOfVector());
		
		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);
		list.add(r5);

		return list;
		
	}

	// ***************** Admin ********************** //

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object c) {
		// self check
		if (this == c)
			return true;
		// null check
		if (c == null)
			return false;
		// type check and cast
		if (getClass() != c.getClass())
			return false;
		return _PO.equals(((Camera) c).getPO()) && _vUp.equals(((Camera) c).getVUp())
				&& _vTo.equals(((Camera) c).getVTo()) && _vRight.equals(((Camera) c).getVRight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return _PO.toString() + " " + _vUp.toString() + " " + _vTo.toString() + " " + _vRight.toString();
	}

}

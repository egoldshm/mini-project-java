package Elements;
import primitives.*;

/**
 * @author egoldshm 
 * @info 
 * Object to represent a camera in 3D space
 */
public class Camera {
	private Point3D _PO;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;
	
	/**
	 * @return PO point, where the camera is positioned in space
	 */
	public Point3D getPO()
	{
		return new Point3D(_PO);
	}
	/**
	 * @param p PO point, where the camera is positioned in space
	 */
	public void setPO(Point3D p)
	{
		_PO=p;
	}
	
	/**
	 * @return Vector direction the top of the camera
	 */
	public Vector getVUp()
	{
		return _vUp;
	}
	/**
	 * @param v Vector direction the top of the camera
	 */
	public void setVUp(Vector v)
	{
		_vUp=v;
	}
	/**
	 * @return Direct vector direction of the camera
	 */
	public Vector getVTo()
	{
		return _vTo;
	}
	
	/**
	 * @param v Direct vector direction of the camera
	 */
	public void setVTo(Vector v)
	{
		_vTo=v;
	}
	
	/**
	 * @return Vector orientation to the sides of the camera
	 */
	public Vector getVRight()
	{
		return _vRight;
	}
	/**
	 * @param v Vector orientation to the sides of the camera
	 */
	public void setVRight(Vector v)
	{
		_vRight=v;
	}
	
	/**
	 * Constructor with parameters
	 * @param PO point, where the camera is positioned in space
	 * @param vUp Vector direction the top of the camera
	 * @param vTo Direct vector direction of the camera
	 * @param vRight Vector orientation to the sides of the camera
	 */
	public Camera(Point3D PO, Vector vUp, Vector vTo, Vector vRight)
	{
		_PO=PO;
		_vUp=vUp;
		_vTo=vTo;
		_vRight=vRight;
	}
	/**
	 * copy constructor
	 */
	public Camera(Camera c)
	{
		
		_PO=c.getPO();
		_vUp=c.getVUp();
		_vTo=c.getVTo();
		_vRight=c.getVRight();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object c)
	{
		//self check
		if (this == c)
			return true;
		// null check
	    if (c == null)
	        return false;
	    // type check and cast
  	   if (getClass() != c.getClass())
	        return false;
  	   return _PO.equals(((Camera)c).getPO())&&_vUp.equals(((Camera)c).getVUp())&&_vTo.equals(((Camera)c).getVTo())&&_vRight.equals(((Camera)c).getVRight());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return _PO.toString()+" "+_vUp.toString()+" "+_vTo.toString()+" "+_vRight.toString();
	}
	
	/**
	 * Returns the Ray from the camera passing through a certain point
	 * @param Nx
	 * @param Ny
	 * @param x
	 * @param y
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 */
	public Ray constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDistance, double screenWidth, double screenHeight)
	{
		Point3D Pc = _PO.add(_vTo.scalarMultiplication(screenDistance));
		_vRight = _vTo.CrossProductVector(_vUp);
		double Rx=(screenWidth/Nx);
		double Ry=(screenHeight/Ny);
		Point3D P;
		P = Pc.add(_vRight.scalarMultiplication((x-(Nx/2.0d))*Rx-(Rx/2.0d)).subtractVector(_vUp.scalarMultiplication(Ry*(y-Ny/2.0d)-Ry/2.0d)));
		return new Ray(_PO, P.subtract(_PO).normalizationOfVector());
	}
	
}

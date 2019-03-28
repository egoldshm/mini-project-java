package Elements;
import primitives.*;

public class Camera {
	private Point3D _PO;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;
	
	public Point3D getPO()
	{
		return _PO;
	}
	public void setPO(Point3D p)
	{
		_PO=p;
	}
	public Vector getVUp()
	{
		return _vUp;
	}
	public void setVUp(Vector v)
	{
		_vUp=v;
	}
	public Vector getVTo()
	{
		return _vTo;
	}
	public void setVTo(Vector v)
	{
		_vTo=v;
	}
	public Vector getVRight()
	{
		return _vRight;
	}
	public void setVRight(Vector v)
	{
		_vRight=v;
	}
	
	public Camera(Point3D PO, Vector vUp, Vector vTo, Vector vRight)
	{
		_PO=PO;
		_vUp=vUp;
		_vTo=vTo;
		_vRight=vRight;
	}
	public Camera(Camera c)
	{
		_PO=c.getPO();
		_vUp=c.getVUp();
		_vTo=c.getVTo();
		_vRight=c.getVRight();
	}
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
	@Override
	public String toString()
	{
		return _PO.toString()+" "+_vUp.toString()+" "+_vTo.toString()+" "+_vRight.toString();
	}
	
	
}

package primitives;

/**
 * class for material
 */
public class Material {

	private double Kd;
	private double Ks;
	private int nShininess;
	private double _Kr;
	private double _Kt;
	
	// ***************** Constructors ********************** //

	/**
	 * @param kd diffuse factor
	 * @param ks specular factor
	 * @param nShininess Shininess factor
	 */
	public Material(double kd, double ks, int nShininess) {
		Kd = kd;
		Ks = ks;
		this.nShininess = nShininess;
		this._Kr = 0;
		this._Kt = 0;
	}
	
	public Material(double kd, double ks, int nShininess, double kr, double kt)
	{
		Kd = kd;
		Ks = ks;
		this.nShininess = nShininess;
		this._Kr = kr;
		this._Kt = kt;
	}
	/**
	 * Default constructor
	 */
	public Material() {
		Kd = 1;
		Ks = 1;
		this.nShininess = 4;
		this._Kr = 0;
		this._Kt = 0;
	}

	/**
	 * Copy constructor
	 */
	public Material(Material m) {
		Kd = m.getKd();
		Ks = m.getKs();
		this.nShininess = m.getnShininess();
		this._Kr = m.getKr();
		this._Kt = m.getKt();
	}


	// ***************** Getters/Setters ********************** //
	/**
	 * @return the kd diffuse factor
	 */
	public double getKd() {
		return Kd;
	}
	/**
	 * @param kd the diffuse factor to set
	 */
	public void setKd(double kd) {
		Kd = kd;
	}

	/**
	 * @return the ks specular factor
	 */
	public double getKs() {
		return Ks;
	}

	/**
	 * @param ks the specular factor to set
	 */
	public void setKs(double ks) {
		Ks = ks;
	}

	/**
	 * @return the nShininess Shininess factor
	 */
	public int getnShininess() {
		return nShininess;
	}

	/**
	 * @param nShininess the Shininess factor to set
	 */
	public void setnShininess(int nShininess) {
		this.nShininess = nShininess;
	}
	
	/**
	 * @return Kr reflection factor
	 */
	public double getKr()
	{
		return  this._Kr;
	}
	
	/**
	 * @param kr reflection factor
	 */
	public void setKr(double kr)
	{
		this._Kr = kr;
	}
	
	/**
	 * @return Kt transparent factor
	 */
	public double getKt() {
		return this._Kt;
	}
	
	/**
	 * @param kt transparent factor
	 */
	public void setKt(double kt)
	{
		this._Kt = kt;
	}

	// ***************** Admin ********************** //

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//self check
		if (this == obj)
			return true;
		// null check
		if (obj == null)
			return false;
		// type check and cast
		if (getClass() != obj.getClass())
			return false;
		return ( (Material) obj).getKd() == this.getKd() && ( (Material) obj).getKs() == this.getKs() && ( (Material) obj).getnShininess() == this.getnShininess();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ks: " + getKs() +"| kd: " + getKd()+"| nShininess: " + getnShininess();
	}
}

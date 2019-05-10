package primitives;

/**
 * class for material
 */
public class Material {

	/**
	 * @param kd
	 * @param ks
	 * @param nShininess
	 */
	public Material(double kd, double ks, int nShininess) {
		Kd = kd;
		Ks = ks;
		this.nShininess = nShininess;
	}

	private double Kd;
	private double Ks;
	private int nShininess;
	
	// ***************** Constructors ********************** //
	public Material() {
		// TODO Auto-generated constructor stub
	}
	
	
	// ***************** Getters/Setters ********************** //
	/**
	 * @return the kd
	 */
	public double getKd() {
		return Kd;
	}
	/**
	 * @param kd the kd to set
	 */
	public void setKd(double kd) {
		Kd = kd;
	}

	/**
	 * @return the ks
	 */
	public double getKs() {
		return Ks;
	}

	/**
	 * @param ks the ks to set
	 */
	public void setKs(double ks) {
		Ks = ks;
	}

	/**
	 * @return the nShininess
	 */
	public int getnShininess() {
		return nShininess;
	}

	/**
	 * @param nShininess the nShininess to set
	 */
	public void setnShininess(int nShininess) {
		this.nShininess = nShininess;
	}

}

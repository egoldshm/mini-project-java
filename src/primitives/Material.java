package primitives;

import java.util.HashMap;

/**
 * class for material
 */
public class Material {

	private double Kd;
	private double Ks;
	private int nShininess;
	
	// ***************** Constructors ********************** //

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
	

	/**
	 * @param kd
	 * @param ks
	 * @param nShininess
	 */
	public Material() {
		Kd = 5;
		Ks = 5;
		this.nShininess = 1;
	}

	/**
	 * @param kd
	 * @param ks
	 * @param nShininess
	 */
	public Material(Material m) {
		Kd = m.getKd();
		Ks = m.getKs();
		this.nShininess = m.getnShininess();
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

	// ***************** Admin ********************** //

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


	@Override
	public String toString() {
		return "ks: " + getKs() +"| kd: " + getKd()+"| nShininess: " + getnShininess();
	}
}

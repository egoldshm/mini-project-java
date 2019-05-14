/**
 * 
 */
package Geometries;

import java.awt.Color;
import java.util.*;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author egoldshm Interface for basic geometry
 */
public abstract class Geometry implements Intersectable {

	private Color emmission;
	private Material material;



	// ***************** Constructors ********************** //

	public Geometry(Color emmission, Material material) {
		this.emmission = emmission;
		this.material = material;
	}

	public Geometry(Geometry geometry) {
		this.emmission = geometry.emmission;
		this.material = geometry.material;
	}

	public Geometry() {
		this.emmission = new Color(0,0,0);
		this.material = new Material();
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	}

	/**
	 * @param emmission the emmission to set
	 */
	public void setEmmission(Color emmission) {
		this.emmission = emmission;
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	// ***************** Operations ******************** // 

	/**
	 * A function that find the vector normal of geometry in point
	 * 
	 * @param point on the geometry in the normal
	 * @return the vector of the normal
	 */
	public abstract Vector getNormal(Point3D point);

}

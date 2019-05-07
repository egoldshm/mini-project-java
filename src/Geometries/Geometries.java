package Geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable{

    private List<Intersectable> _geometries;

    public Geometries(List<Intersectable> geometries) {
        this._geometries = geometries;
    }

    public Geometries() {
        _geometries = new ArrayList<Intersectable>();
    }

    // ***************** Constructors ********************** //

    public List<Intersectable> getGeometries() {
        return _geometries;
    }

    public void setGeometries(List<Intersectable> geometries) {
        this._geometries = geometries;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
        public Map<Geometry, List<Point3D>> findIntersections(Ray ray)
    {
            Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
            for (Intersectable geometry : _geometries)
            {
                Map<Geometry, List<Point3D>> geometryIntersectionPoints = geometry.findIntersections(ray);
                if(!geometryIntersectionPoints.isEmpty())
                {
                    for (Map.Entry<Geometry, List<Point3D>> geometry1: geometryIntersectionPoints.entrySet())
                    {
                        intersectionPoints.put(geometry1.getKey(),geometry1.getValue());
                    }
                }
            }
            return intersectionPoints;
        }


}

package calculations;

import model.Driver;
import model.Package;
import model.PackagesList;

import java.awt.*;

public class ClosestPoint {
    private int y1;
    private int y2;
    private int x1;
    private int x2;
    private static double distance;
    private Point currentPoint;
    Point[] allDeliveryLocations;

    public void setAllDeliveryLocation(PackagesList pL) {
        for (Package p : pL.allPackages)
    }

    //REQUIRES: x1 and y1 to be drivers current (x,y) location
    //EFFECTS: updates x1 y1 x2 y2 to given corresponding parameters, and sets currentPoint to (x1,y1)
    public ClosestPoint(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        currentPoint = new Point(this.x1,this.y1);

    }

    //getters
    public double getDistance() {
        return distance;
    }

    public void distance() {
        double x = Math.pow(this.x2 - this.x1, 2);
        double y = Math.pow(this.y2 - this.y1, 2);
        distance = Math.sqrt(x + y);
    }

    public double nearestDelivery() {



    }

}

package model;

import model.PackagesList;

import java.awt.*;
import java.util.ArrayList;

/**
 * Driver class contains information required for a driver -> driverName, a unique driverID, driverPhoneNumber, a list of
 * packages needed to be delivered, a license plate number of the drivers car,
 * and a last seen coordinate which is set when the driver has completed the first delivery.
 * Each driver has a MINIMUM_PACKAGES, which is the minimum number of packages needed to be delivered daily,
 * there is no max.
 * A driver is able to add packages, find out how many packages left to deliver, know the location of each package,
 * and ask which package deliver next according to which is closest.
 */
public class Driver {
    private final int MINIMUM_PACKAGES = 10;
    private String driverName;
    private int driverID;
    private int driverPhoneNumber;
    private String licensePlate;
    private Point lastSeenLocation;
    private PackagesList driversDeliveries;
    public Package currentPackageDelivering;
    private static boolean firstDelivery;

    //EFFECTS: constructs a driver with a name, driverID, phoneNumber & the drivers car license plate, and sets location
    // to (0,0) ---> UNKNOWN as of know
    public Driver(String name, int driverID, int phoneNumber, String licensePlate) {
        this.driverName = name;
        this.driverID = driverID;
        this.driverPhoneNumber = phoneNumber;
        this.licensePlate = licensePlate;
        this.lastSeenLocation = new Point(0, 0);     //NOT YET INITIALIZED
        driversDeliveries = new PackagesList();
        firstDelivery = true;

    }

    // getter
    public Package getCurrentPackageDelivering() {
        return currentPackageDelivering;
    }

    //REQUIRES: firstDelivery = true;
    //MODIFIES: this
    //EFFECTS: updates currentPackageDelivering to the first package in driverDeliveries, and its removed from
    //driversDeliveries, the drivers firstDelivery is set to false and lastSeenLocation is updated to the location of
    //currentpackageDelivering
    public void startDelivering() {
        if (firstDelivery) {
            currentPackageDelivering = driversDeliveries.getAllPackages().get(0);
            driversDeliveries.removePackage(currentPackageDelivering);
            firstDelivery = false;
            this.lastSeenLocation = currentPackageDelivering.getDeliveryLocation();
        }
//        else {
//            for (Package p)
//            currentPackageDelivering = driversDeliveries.getNextPackage();
//            this.lastSeenLocation = currentPackageDelivering.getDeliveryLocation();
    }

    //MODIFIES: this
    //EFFECTS: updates drivers currentPackageDelivering to the nearest delivery abnd updates driver lastSeenLocation
    // to the location of currentPackageDelivering
    public void deliverNextPackage() {
        currentPackageDelivering = driversDeliveries.getNextPackage();
        this.lastSeenLocation = currentPackageDelivering.getDeliveryLocation();
    }


    public void addPackage(Package p) {
        driversDeliveries.addPackage(p);
    }

    public void packagesLeft() {
        driversDeliveries.getPackagesInList();
    }


}

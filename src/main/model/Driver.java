package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWritable;

import java.awt.*;
import java.util.Objects;
import java.util.Random;


/**
 * Driver class contains information required for a driver -> driverName, a unique driverID  which is chosen by the user
 * and is must be 5 integers, a list of packages needed to be delivered, a license plate number of
 * the drivers car, and a last seen coordinate which is set when the driver has completed the first delivery.
 * Each driver has a MINIMUM_PACKAGES, which is the minimum number of packages needed to be delivered daily,
 * and a maximum of 35 packages a day.
 * A driver is able to add packages, remove packages, find out how many packages left to deliver,
 * know the location of each package, and find out which package to deliver next according to which is closest.
 */
public class Driver implements JsonWritable {

    public static final int MINIMUM_PACKAGES = 5;
    public static final int MAXIMUM_PACKAGES = 35;
    private final String driverName;
    private final String driverID;
    private final String licensePlate;
    private Point lastSeenLocation;
    private int lastSeenLocationX;
    private int lastSeenLocationY;
    private final PackagesList driversDeliveries;
    private Package currentPackageDelivering;
    private boolean firstDelivery;

    //REQUIRES: driverID to be = 5 integers
    //EFFECTS: constructs a driver with a name, driverID of , phoneNumber & the drivers car license plate,
    // and sets location to (0,0) ---> UNKNOWN as of know
    public Driver(String name, String driverID, String licensePlate) {
        this.driverName = name;
        this.driverID = driverID;
        this.licensePlate = licensePlate;
        lastSeenLocation = new Point(0, 0);     //NOT YET INITIALIZED, since no package delivered yet
        driversDeliveries = new PackagesList();
        currentPackageDelivering = null;
        firstDelivery = true;
    }

    // getters
    public boolean isFirstDelivery() {
        return firstDelivery;
    }

    public PackagesList getDriversDeliveries() {
        return driversDeliveries;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Package getCurrentPackageDelivering() {
        return currentPackageDelivering;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverID() {
        return driverID;
    }

    public Point getLastSeenLocation() {
        return lastSeenLocation;
    }

    public int getLastSeenLocationx() {
        return lastSeenLocationX;
    }

    public int getLastSeenLocationy() {
        return lastSeenLocationY;
    }

    public static int getMinimumPackages() {
        return MINIMUM_PACKAGES;
    }

    public static int getMaximumPackages() {
        return MAXIMUM_PACKAGES;
    }

    //setters
    public void setLastSeenLocationX(int lastSeenLocation) {
        this.lastSeenLocationX = lastSeenLocation;
    }

    public void setLastSeenLocationY(int lastSeenLocation) {
        this.lastSeenLocationY = lastSeenLocation;
    }

    public void setFirstDelivery(boolean trueOrFalse) {
        this.firstDelivery = trueOrFalse;
    }

    public void setLastSeenLocation(Point lastSeenLocation) {
        this.lastSeenLocation = lastSeenLocation;
    }

    //EFFECTS: if driversDeliveres > MINIMUM_PACKAGES return true, else returns false
    public boolean reachedMinimumNumberofDeliveries() {
        return driversDeliveries.getAllPackages().size() >= MINIMUM_PACKAGES;
    }

    //EFFECTS: returns true if driver has no packages left to deliver, false otherwise
    public boolean completedDeliveries() {
        return packagesLeft() == 0;
    }

    //MODIFIES: this
    //EFFECTS:  completes all of the drivers deliveries
    public boolean completeAllDeliveries() {
        driversDeliveries.getAllPackages().clear();
        return true;
    }

    //REQUIRES: Package p does NOT already exist in driversDeliveries
    //MODIFIES: this
    //EFFECTS:  adds Package p to driversDeliveries
    public void addPackage(Package p) {
        if (driversDeliveries.getAllPackages().size() < MAXIMUM_PACKAGES) {
            driversDeliveries.addPackage(p);
        }
    }

    //REQUIRES: Package p to be in driversDeliveries
    //MODIFIES: this
    //EFFECTS:  if driversDelivers > MINIMUM_PACKAGES, remove Package p and return true, otherwise false
    public boolean removePackage(Package p) {
        if (driversDeliveries.getAllPackages().size() > MINIMUM_PACKAGES) {
            driversDeliveries.removePackage(p);
            return true;
        }
        return false;
    }

    //REQUIRES: firstDelivery = true
    //MODIFIES: this
    //EFFECTS:if driversDeliveries >= MINIMUM_PACKAGES, and its there first delivery it updates currentPackageDelivering
    //     to the first package in driverDeliveries, and that package is removed from driversDeliveries, the drivers
    //     firstDelivery is updated to false and lastSeenLocation is updated to the location of CurrentpackageDelivering
    public boolean startDelivering() {
        if (reachedMinimumNumberofDeliveries()) {
            currentPackageDelivering = driversDeliveries.getAllPackages().get(0);
            driversDeliveries.removePackage(currentPackageDelivering);
            firstDelivery = false;
            this.lastSeenLocation = currentPackageDelivering.getDeliveryLocation();
            this.lastSeenLocationX = currentPackageDelivering.getDeliveryLocation().x;
            this.lastSeenLocationY = currentPackageDelivering.getDeliveryLocation().y;
            currentPackageDelivering.setStatusToDelivered();
            return true;
        }
        return false;
    }

    //EFFECTS: returns closest package to drivers lastSeenLocation
    private Package findNearestPackage() {
        Package closest = new Package();
        double distance = 100000.0;
        for (Package p : driversDeliveries.getAllPackages()) {
            double closestDistance = getLastSeenLocation().distance(p.getDeliveryLocation());
            if (closestDistance <= distance) {
                distance = closestDistance;
                closest = p;
            }
        }
        return closest;
    }

    //MODIFIES:this
    //EFFECTS: finds next nearest delivery available, updates drivers currentPackageDelivering to it,
    //         removes currentPackageDelivering from driversDeliveries, sets it to delivered
    //         and updates driver lastSeenLocation to the location of currentPackageDelivering,
    //         returns currentPackageDelivering
    public Package deliverNextPackage() {
        Package closest = findNearestPackage();
        this.currentPackageDelivering = closest;
        this.lastSeenLocation = currentPackageDelivering.getDeliveryLocation();
        currentPackageDelivering.setStatusToDelivered();
        driversDeliveries.removePackage(closest);
        return currentPackageDelivering = closest;
    }

    //REQUIRES: firstDelivery = false
    //EFFECTS:  returns the closest package to the driver
    public Package checkNearestPackage() {
        return findNearestPackage();
    }

    //EFFECTS: returns an int of how many packages are left to deliver
    public int packagesLeft() {
        return driversDeliveries.getAllPackages().size();
    }

    //MODIFIES: this
    //EFFECTS:  generates a random unique number (including boundaries) from -> [1000,9999]
    public int generateRandomNumber() {
        Random rand = new Random();
        int r2 = 1000 + rand.nextInt(10000 - 1000);
        return r2;
    }

    @Override
    public JSONObject toJson() {
        JSONObject driverJson = new JSONObject();
        driverJson.put("driverName", this.driverName);
        driverJson.put("driverID", this.driverID);
        driverJson.put("licensePlate", this.licensePlate);
        driverJson.put("lastSeenLocationX", this.getLastSeenLocationx());
        driverJson.put("lastSeenLocationY", this.getLastSeenLocationy());
        driverJson.put("driversDeliveries", deliveriesToJson());
        driverJson.put("currentPackageDelivering", this.currentPackageDelivering);
        driverJson.put("firstDelivery", this.firstDelivery);
        return driverJson;
    }

    //MODIFIES: deliveriesArrayJson
    //EFFECTS: returns driversDeliveries stored as a JSON array
    public JSONArray deliveriesToJson() {
        JSONArray deliveriesArrayJson = new JSONArray();

        for (Package deliveries : this.driversDeliveries.getAllPackages()) {
            deliveriesArrayJson.put(deliveries.toJson());
        }
        return deliveriesArrayJson;
    }
}


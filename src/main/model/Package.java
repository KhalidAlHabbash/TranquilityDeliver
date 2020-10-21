package model;

import org.json.JSONObject;
import persistence.JsonWritable;

import java.awt.Point;
import java.util.Date;
import java.util.Random;

/**
 * Package class represents a package consisting of a packageID, unique for each package.
 * As well as a coordinate deliveryLocation ([0-100],[0-100]) not including 0, but including 100, a customerName,
 * dateOrdered, and package delivery status.
 */
public class Package implements JsonWritable {

    private int packageID;
    private String customerPhoneNumber;
    private Point deliveryLocation;
    private String customerName;
    private String dateOrdered;
    private boolean deliveryStatus;

    //getters
    public String getCustomerPhoneNumber() {
        return this.customerPhoneNumber;
    }
    
    public Point getDeliveryLocation() {
        return this.deliveryLocation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public boolean getDeliveryStatus() {
        return this.deliveryStatus;
    }

    //setter
    public void setStatusToDelivered() {
        this.deliveryStatus = true;
    }

    //EFFECTS: creates a Package object with no information
    public Package() {
    }

    //EFFECTS: constructs a new package with customerPhoneNumber, deliveryLocation, customerName, and dateOrdered
    public Package(String customerPhoneNumber, Point deliveryLocation, String customerName, String dateOrdered) {
        this.customerPhoneNumber = customerPhoneNumber;
        this.deliveryLocation = deliveryLocation;
        this.customerName = customerName;
        this.dateOrdered = dateOrdered;
        this.packageID = generateRandomNumber();
        deliveryStatus = false;
    }

    //MODIFIES: this
    //EFFECTS: generates a random unique number (including boundaries) from -> [1000,9999]
    public int generateRandomNumber() {
        Random rand = new Random();
        int r2 = 1000 + rand.nextInt(10000 - 1000);
        return r2;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonPackage = new JSONObject();
        jsonPackage.put("packageID", this.packageID);
        jsonPackage.put("customerPhoneNumber", this.customerPhoneNumber);
        jsonPackage.put("deliveryLocation", this.deliveryLocation);
        jsonPackage.put("customerName", this.customerName);
        jsonPackage.put("dateOrdered", this.dateOrdered);
        jsonPackage.put("deliveryStatus", this.deliveryStatus);
        return jsonPackage;
    }
}


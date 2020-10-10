package model;

import java.awt.Point;
import java.util.Date;
import java.util.Random;

/**Package class represents a package consisting of a special int packageID, unique for each package.
 * As well as a coordinate deliveryLocation ([0-100],[0-100]) not including 0, but including 100, a customerName,
 * datePackageIntiated, and checking whether a package is delivered or not (isDelivered).
 *
 *
 */
public class Package {
    private int packageID;
    private int customerPhoneNumber;
    private Point deliveryLocation;
    private String customerName;
    private Date dateOrdered;
    private boolean deliveryStatus;

    public Package() {
    }

    public Package(int customerPhoneNumber, Point deliveryLocation, String customerName, Date dateOrdered) {
        this.customerPhoneNumber = customerPhoneNumber;
        this.deliveryLocation = deliveryLocation;
        this.customerName = customerName;
        this.dateOrdered = dateOrdered;
        this.packageID = generateRandomNumber();
    }

    //getters
    public int getPackageID() {
        return packageID;
    }

    public int getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public Point getDeliveryLocation() {
        return deliveryLocation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public boolean getStatus() {
        return deliveryStatus;
    }

    //setters
    public void setStatusToDelivered() {
        deliveryStatus = true;
    }

    //MODIFIES: this
    //EFFECTS: generates a random unique number (including boundaries) from -> [1000,9999]
    public int generateRandomNumber() {
        Random rand = new Random();
        int r2 = 1000 + rand.nextInt(10000 - 1000);
        return r2;
    }


}


package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private Driver dTest;
    private Package p1;
    private Package p2;
    private Package p3;
    private Package p4;
    private Package p5;
    private Package p6;
    private Package p7;
    private Package p8;

    @BeforeEach
    public void setUp() {
        dTest = new Driver("Khalid", "68653", "G1BL1");
        p1 = new Package("778222777", new Point(24, 88), "Jay",
                "10/09/2020");
        p2 = new Package("778222343", new Point(52, 90), "Michael Jackson",
                "10/05/2020");
        p3 = new Package("604558881", new Point(54, 92), "BeetleJuice",
                "5/02/2020");
        p4 = new Package("604258881", new Point(34, 70), "BeetleJuice",
                "7/22/2020");
        p5 = new Package("604259881", new Point(26, 89), "BeetleJuice",
                "4/15/2020");
        p6 = new Package("604259981", new Point(15, 89), "Josh",
                "2/16/2020");
        p7 = new Package("604259221", new Point(26, 66), "Asma",
                "1/01/2020");
        p8 = new Package("604259341", new Point(18, 89), "Dana",
                "4/15/2020");
    }

    @Test
    public void testConstructor() {
        assertEquals("Khalid", dTest.getDriverName());
        assertEquals("G1BL1", dTest.getLicensePlate());
        assertEquals(0, dTest.getLastSeenLocation().x);
        assertEquals(0, dTest.getLastSeenLocation().y);
        assertEquals(0, dTest.getDriversDeliveries().getAllPackages().size());
        assertTrue(dTest.isFirstDelivery());
        assertEquals("68653", dTest.getDriverID());
    }

    @Test
    public void teststartDelivering() {
        //testing when driversDeliveries < MINIMUM_PACKAGES
        dTest.addPackage(p2);
        for (int i = 0; i < Driver.MINIMUM_PACKAGES - 2; i++) {
            dTest.addPackage(p1);               //4 packages
        }
        assertFalse(dTest.startDelivering());

        //testing when driversDeliveries has reached MINIMUM_PACKAGES
        dTest.addPackage(p3);
        assertTrue(dTest.startDelivering());
        assertEquals(p2, dTest.getCurrentPackageDelivering());
        assertFalse(dTest.getDriversDeliveries().contains(p2));
        assertFalse(dTest.isFirstDelivery());
        assertEquals(p2.getDeliveryLocation(), dTest.getLastSeenLocation());
    }

    @Test
    public void testDeliverNextPackageWithClosePoint() {
        dTest.addPackage(p2);
        dTest.addPackage(p1);
        dTest.addPackage(p3);
        dTest.addPackage(p4);
        dTest.addPackage(p5);    //5 packages

        dTest.startDelivering();   //4 packages

        assertEquals(p3, dTest.deliverNextPackage());  //3 packages
        assertEquals(p3.getDeliveryLocation(), dTest.getLastSeenLocation());
        assertEquals(p3, dTest.getCurrentPackageDelivering());
        assertFalse(dTest.getDriversDeliveries().contains(p3));
        assertEquals(3, dTest.packagesLeft());
    }

    @Test
    public void testDeliverNextPackageWithFarPoint() {
        dTest.addPackage(p4);
        dTest.addPackage(p2);
        dTest.addPackage(p3);
        dTest.addPackage(p1);
        dTest.addPackage(p5);          //5 packages

        dTest.startDelivering();    //4 packages

        assertEquals(p1, dTest.deliverNextPackage());   //3 packages
        assertEquals(p1.getDeliveryLocation(), dTest.getLastSeenLocation());
        assertEquals(p1, dTest.getCurrentPackageDelivering());
        assertFalse(dTest.getDriversDeliveries().contains(p1));
        assertEquals(3, dTest.packagesLeft());
    }

    @Test
    public void testDeliverNextPackageWithSamePointDelivery() {
        dTest.addPackage(p1);
        dTest.addPackage(p1);
        dTest.addPackage(p2);
        dTest.addPackage(p3);
        dTest.addPackage(p4);
        dTest.addPackage(p5);   //6 packages

        dTest.startDelivering();    // 5 packages

        assertEquals(p1, dTest.deliverNextPackage());   //4 packages
        assertEquals(p1.getDeliveryLocation(), dTest.getLastSeenLocation());
        assertEquals(p1, dTest.getCurrentPackageDelivering());
        assertFalse(dTest.getDriversDeliveries().contains(p1));
        assertEquals(4, dTest.packagesLeft());
    }

    @Test
    public void testreachedMinimumNumberofDeliveries() {
        dTest.addPackage(p2);
        for (int i = 0; i < Driver.MINIMUM_PACKAGES - 2; i++) {
            dTest.addPackage(p1);               // 9 packages, one less than MINIMUM_PACKAGES
        }
        assertFalse(dTest.reachedMinimumNumberofDeliveries());

        dTest.addPackage(p4);
        assertTrue(dTest.reachedMinimumNumberofDeliveries());
    }

    @Test
    public void testCheckNearestPackage() {
        dTest.addPackage(p4);
        dTest.addPackage(p2);
        dTest.addPackage(p3);
        dTest.addPackage(p1);
        dTest.addPackage(p5);

        dTest.startDelivering();

        assertEquals(p1, dTest.checkNearestPackage());
    }

    @Test
    public void testaddPackage() {
        //tests both addPackage and packagesLeft method
        assertEquals(0, dTest.packagesLeft());
        dTest.addPackage(p1);
        assertEquals(1, dTest.packagesLeft());
        dTest.addPackage(p2);
        assertEquals(2, dTest.packagesLeft());
        dTest.addPackage(p3);
        assertEquals(3, dTest.packagesLeft());

        for (int i = 0; i <= Driver.MAXIMUM_PACKAGES - 3; i++) {
            dTest.addPackage(p1);                                   //contains MAXIMUM_PACKAGES
        }
        assertEquals(35, dTest.packagesLeft());
        dTest.addPackage(p5);
        assertEquals(35, dTest.packagesLeft());
    }

    @Test
    public void testRemovePackage() {
        //testing when driversDeliveries < MINIMUM_PACKAGES
        dTest.addPackage(p1);
        dTest.addPackage(p2);
        dTest.addPackage(p3);
        dTest.addPackage(p4);
        dTest.addPackage(p5);
        assertEquals(5, dTest.packagesLeft());
        dTest.removePackage(p1);
        assertEquals(5, dTest.packagesLeft());

        //testing when driversDeliveries > MINIMUM_PACKAGES by 1
        dTest.addPackage(p6);
        assertEquals(6, dTest.packagesLeft());
        dTest.removePackage(p6);
        assertEquals(5, dTest.packagesLeft());

        //testing when driversDeliveries > MINIMUM_PACKAGES by more than 1
        dTest.addPackage(p6);
        dTest.addPackage(p7);
        dTest.addPackage(p8);
        assertEquals(8, dTest.packagesLeft());
        dTest.removePackage(p1);
        assertEquals(7, dTest.packagesLeft());
        dTest.removePackage(p2);
        assertEquals(6, dTest.packagesLeft());
        dTest.removePackage(p3);
        assertEquals(5, dTest.packagesLeft());
        dTest.removePackage(p4);
        assertEquals(5, dTest.packagesLeft());
    }

    @Test
    public void testgenerateRandomNumber() {
        int timesRepeated = 0;
        int lastNumber = 0;
        for (int i = 0; i < 50000; i++) {
            int currentNum = dTest.generateRandomNumber();
            if (currentNum == lastNumber) {
                timesRepeated++;
                lastNumber = currentNum;
            } else {
                lastNumber = currentNum;
            }
        }
        assertFalse(timesRepeated >= 15);
    }

    @Test
    public void testgetLastSeenLocationxy() {
        dTest.addPackage(p1);
        dTest.addPackage(p2);
        dTest.addPackage(p3);
        dTest.addPackage(p4);
        dTest.addPackage(p5);

        dTest.startDelivering();
        assertEquals(24, dTest.getLastSeenLocationx());
        assertEquals(88, dTest.getLastSeenLocationy());
    }

    @Test
    public void testcompletedDeliveries() {
        //when driver has no packages to deliver in his driverDeliveries
        assertTrue(dTest.completedDeliveries());

        //driver has packages
        dTest.addPackage(p1);
        assertFalse(dTest.completedDeliveries());
        dTest.addPackage(p2);
        assertFalse(dTest.completedDeliveries());
    }

    @Test
    public void testcompleteAllDeliveries() {
        dTest.addPackage(p3);
        dTest.addPackage(p4);
        dTest.addPackage(p5);
        assertEquals(3, dTest.getDriversDeliveries().getAllPackages().size());

        dTest.completeAllDeliveries();
        assertEquals(0, dTest.getDriversDeliveries().getAllPackages().size());
    }
}








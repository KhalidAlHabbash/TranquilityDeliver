package ui.popups;

import model.Driver;
import model.Package;
import org.w3c.dom.ranges.Range;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberOfPackagesPopUp {
    private Driver driver;
    int input;

    public NumberOfPackagesPopUp(Driver d) {
        this.driver = d;
        numberOfDeliveries();
    }

    private void numberOfDeliveries() {
//        JOptionPane popUp = new JOptionPane();
//        String value = popUp.showInputDialog("Number of packages to deliver:", 0);
//        int choice = Integer.parseInt(value);
//        continueFromResult(choice);
        Object[] choices = {15,10,5};
         input = JOptionPane.showOptionDialog(null, "Choose from the following options:",
                "Packages", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, choices, choices[0]);

        continueFromResult(input);

    }

    private void continueFromResult(int choice) {
        if (choice == JOptionPane.INFORMATION_MESSAGE) {
            generatePackagesforDriver(10, driver);
            new TranquilityDeliveryApp(driver);
        }
        if (choice == JOptionPane.ERROR_MESSAGE) {
            generatePackagesforDriver(15, driver);
            new TranquilityDeliveryApp(driver);
        }
        if (choice == JOptionPane.WARNING_MESSAGE) {
            generatePackagesforDriver(5, driver);
            new TranquilityDeliveryApp(driver);
        }
    }

//        JSpinner n = new JSpinner();
//        n.setBackground(Color.gray);
//        int input = n.showOptionDialog(null, "# of Packages", " Packages",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0);
//
//        if (input == JOptionPane.OK_OPTION) {
//            generatePackagesforDriver(input,driver);
//            new TranquilityDeliveryApp(driver);


//        JOptionPane n = new JOptionPane();
//        int numberOfDeliveries = Integer.parseInt(n.showInputDialog(
//                null, "# of Packages: ",0));
//        n.action
//
//        return numberOfDeliveries;

    //MODIFIES: this
    //EFFECTS: adds packages to the drivers deliveries
    private void generatePackagesforDriver(int choice, Driver d) {
        String[] names;
        names = new String[]{"Khalid", "Jay", "Dana", "Josh", "Michael", "Jack", "Saif", "Yara", "Selena", "Kylie",
                "Lulwa", "Justin", "Emily", "Scofield", "Johan", "Spencer", "Herbert", "Vidales", "Anthony", "Cedric",
                "Sara", "Sarah", "Maria", "Susu", "Sophie", "Manveer", "Lutfi", "Ismail", "Yasmeen", "Reina",
                "Faisal", "Dardas", "Colo", "Mohammed", "Moe"};
        for (int i = 0; i < choice; i++) {
            int x = new Random().nextInt(12 + 1) - 1;
            Package addPackagesToDriver = new Package("60" + driver.getDriverID() + "1",
                    new Point(new Random().nextInt(99), new Random().nextInt(99)), names[i],
                    x + "/2020");
            d.addPackage(addPackagesToDriver);
        }
    }
}


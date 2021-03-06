package ui.popups;

import model.Driver;
import model.Package;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.StringJoiner;

public class ShowAllPackages {
    private final Driver driver;

    //sets up and creates a new driver
    public ShowAllPackages(Driver d) {
        JFrame deliveresFrame = new JFrame();
        deliveresFrame.setBackground(Color.lightGray);
        deliveresFrame.setSize(500, 500);
        this.driver = d;
        deliveresFrame.setTitle(driver.getDriverName());
        showOffDeliveries(deliveresFrame);

        deliveresFrame.setVisible(true);
    }

    //getters
    public Driver getDsetUp() {
        return this.driver;
    }

    private void showOffDeliveries(JFrame deliveresFrame) {
        JLabel allPackages = new JLabel("Packages to deliver: ", JLabel.LEFT);
        allPackages.setVerticalAlignment(JLabel.TOP);
        allPackages.setBounds(0, 0, 200, 25);
        deliveresFrame.add(allPackages);
        JLabel deliveries = new JLabel("", JLabel.LEFT);
        deliveries.setBounds(0, 0, 200, 25);
        deliveries.setText(printPackages(driver.getDriversDeliveries().getAllPackages()));
        deliveresFrame.add(deliveries);
    }

    private String printPackages(List<Package> allPackages) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Package p : allPackages) {
            joiner.add(p.getCustomerName() + " to " + "(" + p.getDeliveryLocation().x + ","
                    + p.getDeliveryLocation().y + ")" + "\n");
        }
        return joiner.toString();
    }

}

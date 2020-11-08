package ui.PopUps;

import model.Driver;
import model.Package;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.StringJoiner;

public class ShowAllPackages {
    private Driver driver;

    //getters
    public Driver getDsetUp() {
        return this.driver;
    }

    //sets up and creates a new driver
    public ShowAllPackages(Driver d) {
        JFrame deliveresFrame = new JFrame();
        deliveresFrame.setBackground(Color.BLUE);
        deliveresFrame.setSize(500, 500);
        this.driver = d;
        deliveresFrame.setTitle(driver.getDriverName());
        showOffDeliveries(deliveresFrame);

        deliveresFrame.setVisible(true);
    }

    private void showOffDeliveries(JFrame deliveresFrame) {
        JLabel allPackages = new JLabel("Packages of the day:",JLabel.LEFT);
        allPackages.setVerticalAlignment(JLabel.TOP);
        allPackages.setBounds(0, 0, 200, 25);
        deliveresFrame.add(allPackages);
        JLabel deliveries = new JLabel("",JLabel.LEFT);
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

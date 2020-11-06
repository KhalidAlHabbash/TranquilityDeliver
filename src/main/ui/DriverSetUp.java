package ui;

import model.Driver;
import model.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *  Creates a system console that sets up a new driver with name, ID, licensePlate, and number of packages to deliver
 */
public class DriverSetUp {
    private Driver dsetUp;
    private String driverID;
    private String driverName;
    private String driverLicensePlate;

    //getters
    public Driver getDsetUp() {
        return this.dsetUp;
    }

    public String getDriverID() {
        return this.driverID;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getDriverLicensePlate() {
        return this.driverLicensePlate;
    }

    //sets up and creates a new driver
    public DriverSetUp() {
        JFrame driverFrame = new JFrame();
        JPanel driverPanel = new JPanel();

        driverFrame.setSize(300, 250);
        driverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        driverFrame.add(driverPanel);
        driverPanel.setLayout(null);

        String driverName = setUpDriverName(driverPanel);
        String driverID = setUpDriverID(driverPanel);
        String driverLicensePlate = setUpLicensePlate(driverPanel);
        int numberofPackages = Integer.parseInt(numberOfDeliveries(driverPanel));

        dsetUp = new Driver(driverName, driverID, driverLicensePlate);
        generatePackagesforDriver(numberofPackages);

        signInButton(driverPanel);
        driverFrame.setVisible(true);
    }

    private String numberOfDeliveries(JPanel driverPanel) {
        JLabel numberOfDelivery = new JLabel("# of packages: ");
        numberOfDelivery.setBounds(10, 169, 200, 25);
        driverPanel.add(numberOfDelivery);
        JFormattedTextField deliveryField = new JFormattedTextField(20);
        deliveryField.setBounds(100, 169, 165, 25);
        String numberOfDeliveries = deliveryField.getText();
        driverPanel.add(deliveryField);

        return numberOfDeliveries;
    }

    //MODIFIES: this
    //EFFECTS: adds packages to the drivers deliveries
    private void generatePackagesforDriver(int num) {
        String[] names;
        names = new String[]{"Khalid", "Jay", "Dana", "Josh", "Michael", "Jack", "Saif", "Yara", "Selena", "Kylie",
                "Lulwa", "Justin", "Emily", "Scofield", "Johan", "Spencer", "Herbert", "Vidales", "Anthony", "Cedric",
                "Sara", "Sarah", "Maria", "Susu", "Sophie", "Manveer", "Lutfi", "Ismail", "Yasmeen", "Reina",
                "Faisal", "Dardas", "Colo", "Mohammed", "Moe"};
        for (int i = 0; i < num; i++) {
            int x = new Random().nextInt(12 + 1) - 1;
            Package addPackagesToDriver = new Package("60" + driverID + "1",
                    new Point(new Random().nextInt(99), new Random().nextInt(99)), names[i],
                    x + "/2020");
            dsetUp.addPackage(addPackagesToDriver);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up driverName field
    private String setUpDriverName(JPanel driverPanel) {
        JLabel nameLabel = new JLabel("Driver name: ");
        nameLabel.setBounds(10, 20, 200, 25);
        driverPanel.add(nameLabel);
        JTextField nameField = new JTextField(20);
        String driverName = nameField.getText();
        nameField.setBounds(100, 20, 165, 25);
        driverPanel.add(nameField);
        return driverName;
    }

    //MODIFIES: this
    //EFFECTS: sets up driverID field
    private String setUpDriverID(JPanel driverPanel) {
        JLabel idLabel = new JLabel("driver ID: ");
        idLabel.setBounds(10, 68, 200, 25);
        driverPanel.add(idLabel);
        JTextField idField = new JTextField(5);
        idField.setBounds(100, 68, 165, 25);
        String driverID = idField.getText();
        driverPanel.add(idField);
        return driverID;
    }

    //MODIFIES: this
    //EFFECTS: sets up driverLicensePlate field
    private String setUpLicensePlate(JPanel driverPanel) {
        JLabel licenseLabel = new JLabel("License plate: ");
        licenseLabel.setBounds(10, 120, 200, 25);
        driverPanel.add(licenseLabel);
        JTextField licenseField = new JTextField(10);
        licenseField.setBounds(100, 120, 165, 25);
        String driverLicensePlate = licenseField.getText();
        driverPanel.add(licenseField);
        return driverLicensePlate;
    }

    //EFFECTS: when signIn button clicked, starts a new TranquilityDeliveryAppGUI
    private void signInButton(JPanel driverPanel) {
        JButton signIn = new JButton("Sign in");
        signIn.setBounds(100, 200, 80, 25);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TranquilityDeliveryAppGUI();
            }
        });
        driverPanel.add(signIn);
    }
}


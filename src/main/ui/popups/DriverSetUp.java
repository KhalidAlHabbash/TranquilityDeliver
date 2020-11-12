package ui.popups;

import model.Driver;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a system console that sets up a new driver with name, ID, licensePlate, and number of packages to deliver
 */
public class DriverSetUp {
    private Driver driverSetUp;
    private JFrame driverFrame;

    //getters
    public Driver getDriver() {
        return this.driverSetUp;
    }

    //sets up and creates a new driver
    public DriverSetUp() {
        driverFrame = new JFrame();
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        driverFrame.setLocation(dim.width / 2 - driverFrame.getSize().width / 2, dim.height / 2
//                - driverFrame.getSize().height / 2);

        JPanel driverPanel = new JPanel();
        driverPanel.setBackground(Color.lightGray);

        driverFrame.setSize(350, 320);

        driverFrame.add(driverPanel);
        driverPanel.setLayout(null);

        String driverName = setUpDriverName(driverPanel);
        String driverID = setUpDriverID(driverPanel);
        String driverLicensePlate = setUpLicensePlate(driverPanel);
        JLabel welcomeMessage = new JLabel("Tranquility Deliver");
        welcomeMessage.setBounds(93, 30, 200, 25);
        welcomeMessage.setFont(welcomeMessage.getFont().deriveFont(20.0f));
        driverPanel.add(welcomeMessage);

        driverSetUp = new Driver(driverName, driverID, driverLicensePlate);

        signInButton(driverPanel);
        driverFrame.setLocationRelativeTo(null);
        driverFrame.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: sets up driverName field
    private String setUpDriverName(JPanel driverPanel) {
        JLabel nameLabel = new JLabel("Driver name: ");
        nameLabel.setBounds(20, 90, 200, 25);
        driverPanel.add(nameLabel);
        JTextField nameField = new JTextField(20);
        nameField.setBounds(110, 90, 165, 25);
        driverPanel.add(nameField);
        String driverName = nameField.getText();
        return driverName;
    }

    //MODIFIES: this
    //EFFECTS: sets up driverID field
    private String setUpDriverID(JPanel driverPanel) {
        JLabel idLabel = new JLabel("Driver ID: ");
        idLabel.setBounds(20, 145, 200, 25);
        driverPanel.add(idLabel);
        JTextField idField = new JTextField(5);
        idField.setBounds(110, 145, 165, 25);
        String driverID = idField.getText();
        driverPanel.add(idField);
        return driverID;
    }

    //MODIFIES: this
    //EFFECTS: sets up driverLicensePlate field
    private String setUpLicensePlate(JPanel driverPanel) {
        JLabel licenseLabel = new JLabel("License plate: ");
        licenseLabel.setBounds(20, 200, 200, 25);
        driverPanel.add(licenseLabel);
        JTextField licenseField = new JTextField(10);
        licenseField.setBounds(110, 200, 165, 25);
        String driverLicensePlate = licenseField.getText();
        driverPanel.add(licenseField);
        return driverLicensePlate;
    }

    //EFFECTS: when signIn button clicked, starts a new TranquilityDeliveryAppGUI
    private void signInButton(JPanel driverPanel) {
        JButton signIn = new JButton("Sign in");
        signIn.setBounds(135, 250, 80, 25);
        driverPanel.add(signIn);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverFrame.dispose();
                new NumberOfPackagesPopUp(driverSetUp);
            }
        });

    }
}


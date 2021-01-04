package ui.buttons;

import model.Package;
import ui.CurrentTranquilityDeliveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddPackageButton extends Button {
    private final List<Package> packages = new ArrayList<>();

    //EFFECTS: constructs a add package button and creates packages
    public AddPackageButton(CurrentTranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
        createPackages();
    }

    //EFFECTS: creates packages
    private void createPackages() {
        Package p1 = new Package("778222777", new Point(24, 88), "Jay",
                "10/09/2020");
        Package p2 = new Package("778222343", new Point(52, 90), "Michael Jackson",
                "10/05/2020");
        Package p3 = new Package("604558881", new Point(54, 92), "Ronaldo",
                "5/02/2020");
        Package p4 = new Package("604258881", new Point(34, 70), "Macy",
                "7/22/2020");
        Package p5 = new Package("604259881", new Point(26, 89), "Alex",
                "4/15/2020");
        Package p6 = new Package("604259981", new Point(15, 89), "Josh",
                "2/16/2020");
        Package p7 = new Package("604259221", new Point(26, 66), "Asma",
                "1/01/2020");
        Package p8 = new Package("604259341", new Point(18, 89), "Dana",
                "4/15/2020");

        addPackages(p1, p2, p3, p4, p5, p6, p7, p8);


    }

    //MODIFIES: this
    //EFFECTS : adds packages to packages array list
    private void addPackages(Package p1, Package p2, Package p3, Package p4, Package p5, Package p6, Package p7,
                             Package p8) {
        packages.add(p1);
        packages.add(p2);
        packages.add(p3);
        packages.add(p4);
        packages.add(p5);
        packages.add(p6);
        packages.add(p7);
        packages.add(p8);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Packages");
        addToParent(parent);

    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddPackagesButton());
    }

    /**
     *     implements an action when addPackages button is clicked
     */
    private class AddPackagesButton implements ActionListener {

        @Override
        //EFFECTS: adds a packages to drivers packages to deliver
        public void actionPerformed(ActionEvent e) {
            for (Package p : packages) {
                driverB.addPackage(p);
                packages.remove(p);
                break;
            }
        }
    }
}


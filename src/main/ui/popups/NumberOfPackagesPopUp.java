package ui.popups;

import model.Driver;
import model.Package;
import ui.TranquilityDeliveryApp;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NumberOfPackagesPopUp {
    private Driver driver;
    private int input;

    public NumberOfPackagesPopUp(Driver d) {
        this.driver = d;
        initializeSound();
        numberOfDeliveries();
    }

    private void numberOfDeliveries() {
        Object[] choices = {15, 10, 5};
        input = JOptionPane.showOptionDialog(null, "How many packages to deliver today "
                        + driver.getDriverName() + ":", "Packages",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);

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

    private void initializeSound() {
        String soundName = "./data/JavaTDApp.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}


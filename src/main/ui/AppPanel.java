package ui;

import model.Driver;

import javax.swing.*;
import java.awt.*;

/**
 * NOT COMPLETED, SETS UP PANEL TO WHICH THE APP IS RENDERED TOO
 */
public class AppPanel extends JPanel {
    private Driver driver;
    private static final String COMPLETED_DELIVERIES = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;


    public AppPanel(Driver d) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        DriverSetUp driverSetUp = new DriverSetUp();
        this.driver = driverSetUp.getDsetUp();

    }
}

package ui;

import model.Driver;
import model.Package;
import ui.buttons.*;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TranquilityDeliveryApp extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private Driver appDriver;

    private List<Button> buttons;
    private Button activeButton;

    //getters
    public Driver getAppDriver() {
        return appDriver;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public Button getActiveButton() {
        return activeButton;
    }

    // setter
    // MODIFIES: this
    // EFFECTS:  sets the given button as the current activeButton
    public void setActiveTool(Button b) {
        if (activeButton != null) {
            activeButton.deactivate();
        }
        b.activate();
        activeButton = b;
    }

    public TranquilityDeliveryApp(Driver d) {
        super("Tranquility Deliver");
        setBackground(Color.LIGHT_GRAY);
        this.appDriver = d;
        initializeFields();
        initializeGraphics();
//        initializeSound();
        initializeInteraction();
    }

    private void initializeFields() {
        buttons = new ArrayList<>();
        activeButton = null;
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the app will operate, and populates the buttons to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        add(new MyPanel());
        pack();
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
//        DrawCar car = new DrawCar(appDriver.getCurrentPackageDelivering().getDeliveryLocation().x,
//                appDriver.getCurrentPackageDelivering().getDeliveryLocation().y);
        JPanel driverArea = new JPanel();
        driverArea.setBackground(Color.lightGray);
        driverArea.setLayout(new GridLayout(1, 2));
        driverArea.setSize(new Dimension(0, 0));
        add(driverArea, BorderLayout.NORTH);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        AppMouseListener aml = new AppMouseListener();
        addMouseListener(aml);
        addMouseMotionListener(aml);
    }

    // MODIFIES: this
    // EFFECTS:  removes given Shape from currentDrawing
    public void removeFromApp(Package p) {
        appDriver.getDriversDeliveries().getAllPackages().remove(p);
    }


    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel buttonArea = new JPanel();
        buttonArea.setBackground(Color.lightGray);
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.SOUTH);

        SaveButton saveButton = new SaveButton(this, buttonArea);
        buttons.add(saveButton);

        LoadButton loadButton = new LoadButton(this, buttonArea);
        buttons.add(loadButton);

        DeliverNextButton nextPackageButton = new DeliverNextButton(this, buttonArea);
        buttons.add(nextPackageButton);

        ShowDeliveriesButton deliveriesButton = new ShowDeliveriesButton(this, buttonArea);
        buttons.add(deliveriesButton);

        setActiveTool(nextPackageButton);
    }


    // EFFECTS: if activeTool != null, then mousePressedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMousePressed(MouseEvent e) {
        if (activeButton != null) {
            activeButton.buttonPressed(e);
        }
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseReleased(MouseEvent e) {
        if (activeButton != null) {
            activeButton.buttonRelease(e);
        }
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseClicked(MouseEvent e) {
        if (activeButton != null) {
            activeButton.buttonClicked(e);
        }
        repaint();
    }

    private class AppMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current drawing's coordinate system
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, appDriver);
        }
    }

    private void drawPackage(Graphics graphics) {
        final double FILL_PERCENT = 0.75;
        final int OFFSET = (int) (Package.PACKAGE_PIXELS * (1.0 - FILL_PERCENT)) / 2;
        final int SIZE = (int) (Package.PACKAGE_PIXELS * FILL_PERCENT);
        Point packagePosition = appDriver.getCurrentPackageDelivering().getDeliveryLocation();

        graphics.setColor(Color.CYAN);
        graphics.fillRect(packagePosition.getLocation().x + OFFSET,
                packagePosition.getLocation().y + OFFSET,
                SIZE, SIZE);
    }

    class MyPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(250, 250);
        }


    }


}



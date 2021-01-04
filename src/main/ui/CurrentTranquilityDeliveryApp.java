package ui;

import model.Driver;
import model.Package;
import ui.buttons.Button;
import ui.buttons.*;
import ui.images.CarImage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CurrentTranquilityDeliveryApp extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;

    protected Driver appDriver;

    private List<Button> buttons;
    private Button activeButton;
    private JPanel driverArea;

    //EFFECTS: sets app title, background, and driver, and initializes the app
    public CurrentTranquilityDeliveryApp(Driver d) {
        super("Tranquility Deliver");
        setBackground(Color.LIGHT_GRAY);
        this.appDriver = d;
        initializeSound();
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    //getters
    public Driver getAppDriver() {
        return appDriver;
    }

    public void setAppDriver(Driver d) {
        appDriver = d;
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

    //MODIFIES: this
    //EFFECTS: initiates buttons arraylist and sets activeButton to null
    private void initializeFields() {
        buttons = new ArrayList<>();
        activeButton = null;
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the app will operate, and populates the buttons to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        driverArea = new JPanel();
        driverArea.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(driverArea, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createTools();
        pack();
        setVisible(true);

    }

    //EFFECTS: initializes sound
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

        AddPackageButton addPackageButton = new AddPackageButton(this, buttonArea);
        buttons.add(addPackageButton);

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
            initializeSound();
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

    @Override
    //MODIFIES: this
    //EFFECTS : paints on driverArea panel
    public void paint(Graphics g) {
        driverArea.paint(g);
        List<Color> colors = getColors();
        for (Package p : appDriver.getDriversDeliveries().getAllPackages()) {
            for (Color c : colors) {
                g.drawOval(p.getDeliveryLocation().x + 100, p.getDeliveryLocation().y + 100, 15, 15);
                g.fillOval(p.getDeliveryLocation().x + 100, p.getDeliveryLocation().y + 100, 15, 15);
                g.setColor(c);
                colors.remove(c);
                break;
            }
        }
        CarImage carImage = new CarImage();
        BufferedImage img = carImage.getImg();
        try {
            g.drawImage(img, appDriver.getCurrentPackageDelivering().getDeliveryLocation().x + 100,
                    appDriver.getCurrentPackageDelivering().getDeliveryLocation().y + 100, 60, 60,
                    null);
        } catch (NullPointerException e) {
            //pass
        }
    }

    //EFFECTS: adds colors to allColors
    private List<Color> getColors() {
        List<Color> allColors = new ArrayList<>();
        allColors.add(Color.BLUE);
        allColors.add(Color.BLACK);
        allColors.add(Color.CYAN);
        allColors.add(Color.GREEN);
        allColors.add(Color.MAGENTA);
        allColors.add(Color.PINK);
        allColors.add(Color.RED);
        allColors.add(Color.yellow);
        allColors.add(Color.DARK_GRAY);
        allColors.add(Color.ORANGE);
        allColors.add(Color.WHITE);
        allColors.add(Color.darkGray);
        allColors.add(Color.lightGray);
        allColors.add(Color.PINK);
        allColors.add(Color.RED);
        return allColors;
    }

    /**
     * translates mouseEvents
     */
    private class AppMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            initializeSound();
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
}



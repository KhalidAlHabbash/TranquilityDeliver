package ui.buttons;

import model.Driver;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Button {
    protected Driver driverB;
    protected JButton button;
    protected TranquilityDeliveryApp app;
    private boolean active;

    //MODIFIES: this
    //EFFECTS: constructs an inactive button in the app
    public Button(TranquilityDeliveryApp app, JComponent parent) {
        driverB = app.getAppDriver();
        this.app = app;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: default behaviour does nothing
    public void buttonPressed(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void buttonRelease(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void buttonClicked(MouseEvent e) {
    }
}

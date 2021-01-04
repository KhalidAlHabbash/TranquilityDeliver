package ui.buttons;

import ui.CurrentTranquilityDeliveryApp;
import ui.popups.ShowAllPackages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDeliveriesButton extends Button {

    //EFFECTS: constructs a show deliveries button
    public ShowDeliveriesButton(CurrentTranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Deliveries for the day");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AllPackages());
    }

    /**
     * implements an action when show packages button is clicked
     */
    private class AllPackages implements ActionListener {

        @Override
        //EFFECTS: a popup appears the shows all drivers deliveries
        public void actionPerformed(ActionEvent e) {
            new ShowAllPackages(driverB);
        }
    }
}


package ui.buttons;

import ui.popups.ShowAllPackages;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDeliveriesButton extends Button {

    public ShowDeliveriesButton(TranquilityDeliveryApp app, JComponent parent) {
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

    private class AllPackages implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ShowAllPackages(driverB);
        }
    }
}


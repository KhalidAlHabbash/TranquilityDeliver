package ui.buttons;

import ui.CurrentTranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliverNextButton extends Button {

    //EFFECTS: constructs a deliver next package button
    public DeliverNextButton(CurrentTranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Deliver next package");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new DeliverNextPackageClickHandler());
    }

    /**
     * implements an action when deliver next package button is clicked
     */
    private class DeliverNextPackageClickHandler implements ActionListener {

        @Override
        //EFFECTS: delivers the next package when deliver next package button is clicked
        public void actionPerformed(ActionEvent e) {
            if (driverB.isFirstDelivery()) {
                driverB.startDelivering();
            } else {
                driverB.deliverNextPackage();
            }
        }
    }
}




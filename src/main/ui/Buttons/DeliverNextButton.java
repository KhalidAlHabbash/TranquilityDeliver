package ui.Buttons;

import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliverNextButton extends Button {

    public DeliverNextButton(TranquilityDeliveryApp app, JComponent parent) {
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

    private class DeliverNextPackageClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            driverB.deliverNextPackage();
        }
    }
}

package ui.buttons;

import model.Package;
import persistence.JsonFileReader;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadButton extends Button {
    private JsonFileReader reader;
    private static final String JSON_SOURCE = "./data/driverData.json";


    public LoadButton(TranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
        reader = new JsonFileReader(JSON_SOURCE);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        addToParent(parent);
    }

    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    driverB = reader.read();
                    app.setAppDriver(driverB);
                    new TranquilityDeliveryApp(driverB);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}


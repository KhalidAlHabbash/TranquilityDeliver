package ui.buttons;

import persistence.JsonFileReader;
import ui.CurrentTranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadButton extends Button {
    private static final String JSON_SOURCE = "./data/driverData.json";
    private JsonFileReader reader;


    //MODIFIES: this
    //EFFECTS: creates a button and initiates the reader
    public LoadButton(CurrentTranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
        reader = new JsonFileReader(JSON_SOURCE);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            //MODIFES: driverB
            //EFFECTS: reads source file,sets to the new loaded driver and runs a new TranquilityDeliveryApp
            public void actionPerformed(ActionEvent e) {
                try {
                    driverB = reader.read();
                    app.setAppDriver(driverB);
                    new CurrentTranquilityDeliveryApp(driverB);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}


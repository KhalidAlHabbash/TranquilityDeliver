package ui.buttons;

import persistence.JsonFileWriter;
import ui.TranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveButton extends Button {
    private JsonFileWriter writer;
    private static final String JSON_SOURCE = "./data/driverData.json";


    public SaveButton(TranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
        writer = new JsonFileWriter(JSON_SOURCE);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }


    @Override
    protected void addListener() {
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(driverB);
                    writer.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        };

    }
}

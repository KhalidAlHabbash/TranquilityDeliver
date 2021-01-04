package ui.buttons;

import persistence.JsonFileWriter;
import ui.CurrentTranquilityDeliveryApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveButton extends Button {
    private static final String JSON_SOURCE = "./data/driverData.json";
    private JsonFileWriter writer;


    //MODIFES: this
    //EFFECTS: constructs a new save button and initiates the writer
    public SaveButton(CurrentTranquilityDeliveryApp app, JComponent parent) {
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
        button.addActionListener(new ActionListener() {
            @Override
            //MODIFIES: this
            //EFFECTS: opens and writes to intended JSON file
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(driverB);
                    writer.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });
    }
}

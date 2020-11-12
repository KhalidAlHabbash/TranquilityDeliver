package ui.buttons;

import ui.TranquilityDeliveryApp;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DeliverNextButton extends Button {

    public DeliverNextButton(TranquilityDeliveryApp app, JComponent parent) {
        super(app, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Deliver next package");
        initializeSound();
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

    private void initializeSound() {
        String soundName = "JavaTDApp.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

}


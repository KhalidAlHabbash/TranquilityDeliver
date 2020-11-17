package ui.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarImage {
    private BufferedImage img;
    private static final String imageDestination = "./data/carImageJava.png";

    //getter
    public BufferedImage getImg() {
        return img;
    }

    //MODIFIES: this
    //EFFECTS: sets img to the image read from imageDestination
    public CarImage() {
        try {
            img = ImageIO.read(new File(imageDestination));
        } catch (IOException e) {
            System.out.println("Exception in loading image: " + e.toString());
        }

    }
}

package ui.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarImage {
    private BufferedImage img;
    private static final String imageDestination = "carImageJava.png";

    public BufferedImage getImg() {
        return img;
    }

    public CarImage() {
        try {
            img = ImageIO.read(new File(imageDestination));
        } catch (IOException e) {
            System.out.println("Exception in loading image: " + e.toString());
        }

    }
}

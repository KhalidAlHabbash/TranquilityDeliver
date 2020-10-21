package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new TranquilityDeliveryApp();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR 404: Unable to run application, file not found");
        }
    }
}

package ui;

import model.Package;
import model.Driver;
import persistence.JsonFileReader;
import persistence.JsonFileWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//Tranquility Deliver application
public class OldTranquilityDeliveryAppUI {
    private static final String JSON_SOURCE = "./data/driverData.json";
    private Driver dinput;
    private String name;
    private String driverID;
    private String licensePlate;
    private JsonFileWriter jsonWriter;
    private JsonFileReader jsonReader;
    private int count = 1;
    private static boolean isSaved;

    //EFFECTS: constructs jsonReader and jsonWriter and runs app
    public OldTranquilityDeliveryAppUI() throws FileNotFoundException {
        jsonReader = new JsonFileReader(JSON_SOURCE);
        jsonWriter = new JsonFileWriter(JSON_SOURCE);
        runapp();
    }

    //MODIFIES: this
    //EFFECTS: collects driver information, and creates a driver object with it
    public void runapp() {
        System.out.println("-------------------------Tranquility Deliver--------------------------");
        System.out.println("To start delivering please provide us with your details.");
        Scanner name = new Scanner(System.in);
        System.out.println("\nFull name: ");
        this.name = name.nextLine();
        System.out.println("Welcome " + this.name + "!");
        Scanner driverID = new Scanner(System.in);
        System.out.println("\nEnter an ID (=< 5 characters): ");
        this.driverID = driverID.nextLine();
        driverIDgreaterthanfiveError();
        Scanner licensePlate = new Scanner(System.in);
        while (true) {
            System.out.println("\nVehicles license plate: ");
            break;
        }
        this.licensePlate = licensePlate.nextLine();
        while (true) {
            System.out.println("\n" + this.name + " signed in. Please wait as we get packages ready! Thank you.");
            break;
        }
        dinput = new Driver(this.name, this.driverID, this.licensePlate);
        loadPreviousSave();
        getNumberofPackagestoDeliver();
    }

    //EFFECTS: if driversDeliveries > 0 loads previous save
    private void loadPreviousSave() {
        JsonFileReader js = new JsonFileReader("./data/driverData.json");
        try {
            if (js.read().getDriversDeliveries().getAllPackages().size() > 0) {
                loadData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES this
    //EFFECTS: loads data saved in JSON file
    private void loadData() {
        Scanner load = new Scanner(System.in);
        try {
            dinput = jsonReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Continue where you left off? you have "
                + dinput.getDriversDeliveries().getAllPackages().size() + " packages remaining.");
        String loadData = load.nextLine().toLowerCase();
        if (loadData.equals("y") | loadData.equals("Y") | loadData.equals("yes") | loadData.equals("Yes")
                | loadData.equals("YES")) {
            count = 0;
            loadDriverData();
            deliverNext("yes");
        }
    }

    //MODIFIES: this
    //EFFECTS: if driverID entered is > 5, driverID is set automatically
    private void driverIDgreaterthanfiveError() {
        if (this.driverID.length() > 5) {
            System.out.println("\nYou driver ID is greater than 5 integers, we automatically have created an ID for "
                    + "you.");
            this.driverID = "92837";
            System.out.println("Your driverID is " + this.driverID);
        }
    }

    //EFFECTS: collects the number of deliveries a driver wants to deliver today
    public void getNumberofPackagestoDeliver() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nHow many packages would you like to deliver today? \n Note: dont forget there is a "
                + Driver.MINIMUM_PACKAGES + " package minimum rule " + "and a "
                + Driver.MAXIMUM_PACKAGES + " package maximum rule!");
        int choice = input.nextInt();
        if (choice < Driver.MINIMUM_PACKAGES || choice > Driver.MAXIMUM_PACKAGES) {
            System.out.println("Note: dont forget there is a "
                    + Driver.MINIMUM_PACKAGES + " package minimum rule " + "and a "
                    + Driver.MAXIMUM_PACKAGES + " package maximum rule!");
            getNumberofPackagestoDeliver();
        }
        generatePackagesforDriver(choice);
    }

    //MODIFIES: this
    //EFFECTS: adds packages to the drivers deliveries
    private void generatePackagesforDriver(int choice) {
        String[] names;
        names = new String[]{"Khalid", "Jay", "Dana", "Josh", "Michael", "Jack", "Saif", "Yara", "Selena", "Kylie",
                "Lulwa", "Justin", "Emily", "Scofield", "Johan", "Spencer", "Herbert", "Vidales", "Anthony", "Cedric",
                "Sara", "Sarah", "Maria", "Susu", "Sophie", "Manveer", "Lutfi", "Ismail", "Yasmeen", "Reina",
                "Faisal", "Dardas", "Colo", "Mohammed", "Moe"};
        for (int i = 0; i < choice; i++) {
            int x = new Random().nextInt(12 + 1) - 1;
            Package addPackagesToDriver = new Package("60" + driverID + "1",
                    new Point(new Random().nextInt(99), new Random().nextInt(99)), names[i],
                    x + "/2020");
            dinput.addPackage(addPackagesToDriver);
        }
        System.out.println("All packages set, you can start delivering!");
        startDeliveryYesNo();
    }

    //EFFECTS: input to start first delivery or not
    private void startDeliveryYesNo() {
        printDeliveries();
        removeExtraPackages();
        Scanner answer = new Scanner(System.in);
        System.out.println("\n Would you like to start delivering? Y/N");
        String answer1 = answer.nextLine().toLowerCase();
        startDeliveryYesNo(answer1);
    }

    //MODIFIES: this
    //EFFECTS: starts the drivers first delivery based on the users input
    private void startDeliveryYesNo(String answer1) {
        if (answer1.equals("y") | answer1.equals("Y") | answer1.equals("yes") | answer1.equals("Yes")
                | answer1.equals("YES")) {
            dinput.startDelivering();
            System.out.println("\nStarting first delivery to " + "(" + dinput.getLastSeenLocation().x + ","
                    + dinput.getLastSeenLocation().y + ")" + " for "
                    + dinput.getCurrentPackageDelivering().getCustomerName() + ".");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Delivery complete! " + dinput.packagesLeft() + " more to go.");
                }
            })
                    .run();
            nearestDelivery();
        }
        System.out.println("\nCome back when you want to start, goodbye.");
        saveDriverData();
        System.exit(1);
    }

    //EFFECTS: prints the customerName and location of the packages to be delivered
    private void printDeliveries() {
        System.out.println("Your deliveries for the day:");
        for (Package p : dinput.getDriversDeliveries().getAllPackages()) {
            System.out.println("↦ " + p.getCustomerName() + " in " + "(" + p.getDeliveryLocation().x + ","
                    + p.getDeliveryLocation().y + ").");
        }
    }

    //EFFECTS: if the driver has completed all deliveries app ends, otherwise, prints out the nearest package
    //         and asks if the driver wants to deliver or not
    public void nearestDelivery() {
        if (dinput.completedDeliveries()) {
            System.out.println("\nYou have completed all deliveries for the day, please return tomorrow for "
                    + "more packages. Thank you!");
            saveDriverData();
            System.exit(1);
        }
        Scanner input = new Scanner(System.in);
        System.out.println("\nNext nearest delivery at " + "(" + dinput.checkNearestPackage().getDeliveryLocation().x
                + "," + dinput.checkNearestPackage().getDeliveryLocation().y + "), deliver? Y/N.");
        String inputS = input.nextLine().toLowerCase();
        if (inputS.equals("n") | inputS.equals("NO") | inputS.equals("no") | inputS.equals("N")) {
            System.out.println("\nPlease sign in again to continue, goodbye.");
            saveDriverData();
            System.exit(1);
        }
        deliverNext(inputS);
    }

    //MODIFIES:this
    //EFFECTS: if drivers packages to deliver is > MINIMUM_PACKAGES, the driver can remove an extra package, otherwise
    //         no package can be removed
    private void removeExtraPackages() {
        Scanner removePackage = new Scanner(System.in);
        System.out.println("\nWould you like to remove an extra package? Y/N");
        String remove = removePackage.nextLine().toLowerCase();
        if (dinput.getDriversDeliveries().getAllPackages().size() > Driver.MINIMUM_PACKAGES) {
            if (remove.equals("y") | remove.equals("Y") | remove.equals("yes") | remove.equals("Yes")
                    | remove.equals("YES")) {
                Scanner packageName = new Scanner(System.in);
                System.out.println("Which package? Please enter the name of the package owner.");
                String pname = packageName.nextLine().toLowerCase();
                for (Package p : dinput.getDriversDeliveries().getAllPackages()) {
                    if (p.getCustomerName().toLowerCase().equals(pname)) {
                        dinput.getDriversDeliveries().removePackage(p);
                    }
                }
            }
        } else {
            System.out.println("You cannot remove any extra packages.");
        }
    }

    //MODIFIES: this
    //EFFECTS: delivers the next package
    private void deliverNext(String inputS) {
        saveDataAfterDeliveringFive();
        if (inputS.equals("y") | inputS.equals("Y") | inputS.equals("yes") | inputS.equals("Yes")
                | inputS.equals("YES")) {
            dinput.deliverNextPackage();
            count++;
            System.out.println("\nDelivering package to " + "("
                    + dinput.getCurrentPackageDelivering().getDeliveryLocation().x + ","
                    + dinput.getCurrentPackageDelivering().getDeliveryLocation().y + ") for "
                    + dinput.getCurrentPackageDelivering().getCustomerName() + ".");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Delivery complete! " + dinput.packagesLeft() + " more to go.");
                }
            })
                    .run();
        }
        nearestDelivery();
    }

    //MODIFIES: this
    //EFFECTS: after driver completes 5 deliveries, saving data is allowed
    private void saveDataAfterDeliveringFive() {
        if (count >= Driver.MINIMUM_PACKAGES) {
            Scanner save = new Scanner(System.in);
            System.out.println("Continue rest of deliveries tomorrow? ");
            String saveData = save.nextLine().toLowerCase();
            if (saveData.equals("y") | saveData.equals("Y") | saveData.equals("yes") | saveData.equals("Yes")
                    | saveData.equals("YES")) {
                isSaved = true;
                saveDriverData();
                System.exit(1);
            } else {
                isSaved = false;
            }
        }
    }

    //MODIFIES: this
    // EFFECTS: saves the workroom to file
    private void saveDriverData() {
        try {
            jsonWriter.open();
            jsonWriter.write(dinput);
            jsonWriter.close();
            System.out.println("Saved " + dinput.getDriverName() + " to " + JSON_SOURCE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_SOURCE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadDriverData() {
        try {
            dinput = jsonReader.read();
            System.out.println("Loaded " + dinput.getDriverName() + " from " + JSON_SOURCE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SOURCE);
        }
    }
}


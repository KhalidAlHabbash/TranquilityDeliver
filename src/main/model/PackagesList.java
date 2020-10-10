package model;

import java.util.ArrayList;
import java.util.List;

public class PackagesList extends Package {
    public List<Package> allPackages;

    //EFFECTS: constructs an empty package list
    public PackagesList() {
        allPackages = new ArrayList<>();
    }

    //getter
    public List<Package> getAllPackages() {
        return allPackages;
    }

    //REQUIRES: Package p to be one of the packages in allPackages
    //MODIFIES: this
    //EFFECTS: removes package p from allPackages
    public void removePackage(Package p) {
        allPackages.remove(p);
    }

    //REQUIRES: allPackages to not empty
    //EFFECTS: prints out all packages in the drivers list
    public void getPackagesInList() {


    }
}

    //MODIFIES: this
    //EFFECTS: if package list is empty print "No packages left to deliver", else returns the first package closest to
    // the driver and  removes it from the list
    public Package getNextPackage() {
        if (allPackages.size() > 0) {
            for (Package p : allPackages) {

            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: adds Package p to list of packages
    public void addPackage(Package p) {
        allPackages.add(p);
    }





}

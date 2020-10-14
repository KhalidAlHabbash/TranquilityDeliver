package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A list to store all packages a driver will be delivering. With the packagesList,  you are able to check whether
 * the list contains a certain package, add a package, remove a package, and return the next package in the list.
 */
public class PackagesList extends Package {
    protected List<Package> allPackages;
    private Package currentPackage;

    //EFFECTS: constructs an empty package list
    public PackagesList() {
        allPackages = new CopyOnWriteArrayList<>();
    }

    //getter
    public List<Package> getAllPackages() {
        return allPackages;
    }

    //EFFECTS: if allPackages contains Packpage p return true, false otherwise
    public boolean contains(Package p) {
        return allPackages.contains(p);
    }

    //MODIFIES: this
    //EFFECTS: adds Package p to list of packages
    public void addPackage(Package p) {
        allPackages.add(p);
    }

    //REQUIRES: Package p to be one of the packages in allPackages
    //MODIFIES: this
    //EFFECTS: removes package p from allPackages
    public void removePackage(Package p) {
        allPackages.remove(p);
    }

    //REQUIRES: allPackages > 0
    //MODIFIES: this
    //EFFECTS:  returns the next package in the packages list
    public Package getNextPackage() {
        for (Package p : allPackages) {
            currentPackage = p;
            break;
        }
        return currentPackage;
    }
}

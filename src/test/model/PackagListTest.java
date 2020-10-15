package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackagListTest {
    private PackagesList p;
    private Package p1;
    private Package p2;

    @BeforeEach
    public void setUp() {
        p = new PackagesList();
        p1 = new Package();
        p2 = new Package();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, p.getAllPackages().size());
    }

    @Test
    public void testContains() {
        //tests both contains and addPackages methods
        p.addPackage(p1);
        assertFalse(p.contains(p2));
        assertTrue(p.contains(p1));

        p.addPackage(p2);
        assertTrue(p.contains(p1));
        assertTrue(p.contains(p2));
    }

    @Test
    public void testGetNextPackage() {
        p.addPackage(p1);
        assertEquals(p1, p.getNextPackage());
        p.addPackage(p2);
        assertEquals(p1, p.getNextPackage());

        p.removePackage(p1);
        p.addPackage(p2);
        assertEquals(p2, p.getNextPackage());
    }
}

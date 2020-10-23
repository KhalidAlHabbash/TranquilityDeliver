package persistence;

import model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private JsonFileReader jsonReader;
    private Driver dr;
    private final String jsonReaderEmpty = "./data/testReaderEmptyDriver.json";
    private final String jsonReaderNonExistent = "./data/NonExistent.json";
    private final String jsonReaderNotEmpty = "./data/testReaderDriver.json";


    @BeforeEach
    public void setUp() {
    //add later
    }

    @Test
    public void testConstructor() {
        jsonReader = new JsonFileReader(jsonReaderEmpty);
        assertEquals("./data/testReaderEmptyDriver.json", jsonReader.getFileSource());
    }

    @Test
    public void testReadExceptionThrown(){
        jsonReader = new JsonFileReader(jsonReaderNonExistent);
        try{
             dr = jsonReader.read();
            fail("IOException expected, nothing thrown");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    @Test
    public void testReaderEmpty() {
        jsonReader = new JsonFileReader(jsonReaderEmpty);
        try {
             dr = jsonReader.read();
             assertEquals("", dr.getDriverName());
             assertEquals(0,dr.getDriversDeliveries().getAllPackages().size());
             assertEquals(5,dr.getMinimumPackages());
             assertEquals(35,dr.getMaximumPackages());
             assertEquals("",dr.getDriverID());
             assertEquals("",dr.getLicensePlate());
             assertEquals(0,dr.getLastSeenLocationx());
            assertEquals(0,dr.getLastSeenLocationy());
             assertEquals(null,dr.getCurrentPackageDelivering());
             assertTrue(dr.isFirstDelivery());
        } catch (IOException e) {
            fail("IOException should have not been thrown");
        }
    }

    @Test
    public void testReaderNotEmpty() {
        jsonReader = new JsonFileReader(jsonReaderNotEmpty);
        try {
            dr = jsonReader.read();
            assertEquals("Logan", dr.getDriverName());
            assertEquals(2,dr.getDriversDeliveries().getAllPackages().size());
            assertEquals(5,dr.getMinimumPackages());
            assertEquals(35,dr.getMaximumPackages());
            assertEquals("19282",dr.getDriverID());
            assertEquals("5402 VRB",dr.getLicensePlate());
            dr.startDelivering();
            assertEquals(0,dr.getLastSeenLocationx());
            assertEquals(0,dr.getLastSeenLocationy());
            assertTrue(dr.isFirstDelivery());
        } catch (IOException e) {
            fail("IOException should have not been thrown");
        }
    }

}


package persistence;

import model.Driver;
import model.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    private JsonFileWriter jsonWriter;
    private JsonFileReader jsonReader;
    private Driver dr;
    private final String jsonWriterEmpty = "./data/testWriterEmptyDriver.json";
    private final String jsonReaderNonExistent = "./data/my\0illegal:fileName.json";
    private final String jsonWriterNotEmpty = "./data/testWriterDriver.json";
    private Package p1;
    private Package p2;

    @BeforeEach
    public void setUp() {
        p1 = new Package("778222777", new Point(24, 88), "Jay",
                "10/09/2020");
        p2 = new Package("778222343", new Point(52, 90), "Michael Jackson",
                "10/05/2020");
    }

    @Test
    public void testConstructorInvalidFile() {
        try {
            dr = new Driver("Khalid","19828","5401 VRX");
            jsonWriter = new JsonFileWriter(jsonReaderNonExistent);
            jsonWriter.open();
            fail("IOException should have been thrown, file non-existent");
        } catch (IOException e) {
            System.out.println("File not found, or damaged");
        }
    }

    @Test
    public void testWriterEmptyDriver() {
        try {
            dr = new Driver("Khalid","19828","5401 VRX");
            jsonWriter = new JsonFileWriter(jsonWriterEmpty);
            jsonWriter.open();
            jsonWriter.write(dr);
            jsonWriter.close();

            jsonReader = new JsonFileReader(jsonWriterEmpty);
            dr = jsonReader.read();
            assertEquals(0,dr.getDriversDeliveries().getAllPackages().size());
            assertEquals("Khalid",dr.getDriverName());
            assertEquals("19828",dr.getDriverID());
            assertEquals("5401 VRX", dr.getLicensePlate());
            assertEquals(0,dr.getLastSeenLocationx());
            assertEquals(0,dr.getLastSeenLocationy());
            assertEquals(null,dr.getCurrentPackageDelivering());
            assertTrue(dr.isFirstDelivery());
        } catch (IOException e) {
            fail("IOException should have not been thrown");
        }
    }

    @Test
    public void testWriterDriverBeforeDelivering() {
        try{
            dr = new Driver("YasoBoom","97128","64B ARB");
            dr.addPackage(p1);
            jsonWriter = new JsonFileWriter(jsonWriterNotEmpty);
            jsonWriter.open();
            jsonWriter.write(dr);
            jsonWriter.close();
            jsonReader = new JsonFileReader(jsonWriterNotEmpty);
            dr = jsonReader.read();
            assertEquals(1,dr.getDriversDeliveries().getAllPackages().size());
            assertEquals("YasoBoom",dr.getDriverName());
            assertEquals("97128",dr.getDriverID());
            assertEquals("64B ARB", dr.getLicensePlate());
            Package now = dr.getDriversDeliveries().getNextPackage();
            assertEquals("Jay",now.getCustomerName());
            assertEquals("778222777",now.getCustomerPhoneNumber());
            assertEquals("10/09/2020",now.getDateOrdered());
            assertEquals(88,now.getDeliveryLocation().y);
            assertEquals(24,now.getDeliveryLocation().x);
            assertEquals(0,dr.getLastSeenLocationx());
            assertEquals(0,dr.getLastSeenLocationy());
            assertTrue(dr.isFirstDelivery());
            assertEquals(null,dr.getCurrentPackageDelivering());
        } catch (IOException e) {
            fail("IOException should have not been thrown");
        }
    }

    @Test
    public void testWriterBeforeDelivering() {
        try {
            dr = new Driver("Brandon","09121","091 YVR");
            dr.addPackage(p1);
            dr.addPackage(p2);
            dr.addPackage(p2);
            dr.addPackage(p2);
            dr.addPackage(p1);
            jsonWriter = new JsonFileWriter(jsonWriterNotEmpty);
            jsonWriter.open();
            jsonWriter.write(dr);
            jsonWriter.close();
            jsonReader = new JsonFileReader(jsonWriterNotEmpty);
            dr = jsonReader.read();
            assertEquals(5,dr.getDriversDeliveries().getAllPackages().size());
            testWriterWhenDelivering();
        } catch (IOException e) {
            System.out.println("IOException should have not been thrown");
        }
    }

    @Test
    private void testWriterWhenDelivering() throws IOException {
        jsonWriter.open();
        dr.startDelivering();
        dr.deliverNextPackage();
        jsonWriter.write(dr);
        jsonWriter.close();
        dr = jsonReader.read();
        assertFalse(dr.isFirstDelivery());
        assertEquals(3,dr.getDriversDeliveries().getAllPackages().size());
        assertEquals(24,dr.getLastSeenLocationx());
        assertEquals(88,dr.getLastSeenLocationy());
    }
}

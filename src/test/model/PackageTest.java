package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PackageTest {
    private Package p;
    private Package p1;

    @BeforeEach
    public void setUp() {
        p = new Package("604348771", new Point(22, 99), "Khalid", "22/02" +
                "/2022");
        p1 = new Package();
    }

    @Test
    public void testConstructorWithParameters() {
        assertEquals("604348771", p.getCustomerPhoneNumber());
        assertEquals(22, p.getDeliveryLocation().x);
        assertEquals(99, p.getDeliveryLocation().y);
        assertEquals("Khalid", p.getCustomerName());
        assertEquals("22/02/2022", p.getDateOrdered());
    }

    @Test
    public void testConstructor() {
        assertEquals(null, p1.getCustomerName());
        assertEquals(null, p1.getDeliveryLocation());
        assertEquals(null, p1.getDateOrdered());
    }

    @Test
    public void setstatusToBeDelivered() {
        p.setStatusToDelivered();
        assertTrue(p.getDeliveryStatus());
    }
}

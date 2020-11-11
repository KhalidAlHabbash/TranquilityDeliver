package ui;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A car shape that can be positioned anywhere on the screen.
 */
public class DrawCar {
    private double xleft;
    private double ytop;

    /**
     * Constructs a car with a given top left corner
     *
     * @param x the x coordinate of the top left corner
     * @param y the y coordinate of the top left corner
     */
    public DrawCar(double x, double y) {
        xleft = x;
        ytop = y;
    }

    /**
     * Draws the car
     *
     * @param g2 the graphics context
     */
    public void draw(Graphics2D g2) {
        Rectangle2D.Double body
                = new Rectangle2D.Double(xleft, ytop + 10, 60, 10);
        Ellipse2D.Double frontTire
                = new Ellipse2D.Double(xleft + 10, ytop + 20, 10, 10);
        Ellipse2D.Double rearTire
                = new Ellipse2D.Double(xleft + 40, ytop + 20, 10, 10);

        Point2D.Double r1
                = new Point2D.Double(xleft + 10, ytop + 10);
        Point2D.Double r2
                = new Point2D.Double(xleft + 20, ytop);
        Point2D.Double r3
                = new Point2D.Double(xleft + 40, ytop);
        Point2D.Double r4
                = new Point2D.Double(xleft + 50, ytop + 10);

        Line2D.Double frontWindshield
                = new Line2D.Double(r1, r2);
        Line2D.Double roofTop
                = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield
                = new Line2D.Double(r3, r4);

        drawCarParts(g2, body, frontTire, rearTire, frontWindshield, roofTop, rearWindshield);
    }

    //MODIFIES: g2
    //EFFECTS: draws cars body, frontire, reartire, frontwindshield,rooftop, and rearwindshield
    private void drawCarParts(Graphics2D g2, Rectangle2D.Double body, Ellipse2D.Double frontTire, Ellipse2D.Double rearTire, Line2D.Double frontWindshield, Line2D.Double roofTop, Line2D.Double rearWindshield) {
        g2.draw(body);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.draw(frontWindshield);
        g2.draw(roofTop);
        g2.draw(rearWindshield);
    }
}

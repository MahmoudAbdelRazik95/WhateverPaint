/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import Tools.Resizer;
import ToolsPanels.Canvas;
import ToolsPanels.ColorBoxes;
import Tools.Intersection;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;




public abstract class Shapes extends JComponent implements Cloneable {

    private int xPos;
    private int yPos;
    private Color color = Color.BLACK;
    private int penSize;
    private double lengths;
    private double widths;
    private boolean selected = false;
    private Color fillColor = new Color(0, 0, 0, 0f);
    private Shape thisShape;
    private ArrayList<Rectangle2D> resizePoints = new ArrayList();

    public boolean isSelected() {
        return selected;
    }

    public ArrayList<Rectangle2D> getResizePoints() {
        return resizePoints;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getxPos() {
        return xPos;
    }

    public double getLengths() {
        return lengths;
    }

    public void setLengths(double lengths) {
        this.lengths = lengths;
    }

    public double getWidths() {
        return widths;
    }

    public void setWidths(double widths) {
        this.widths = widths;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPenSize() {
        return penSize;
    }

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    public Shape getThisShape() {
        return thisShape;
    }

    public void setThisShape(Shape thisShape) {
        this.thisShape = thisShape;
    }

    public abstract void drawShape(Graphics g);

    public boolean containsPoint(Point p) {
        boolean check = thisShape.getBounds2D().contains(p);

        return check;
    }

    public void setPoint(Point p) {

    }

    protected void drawBoundPoint(int x, int y, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D boundPoint = new Rectangle2D.Double();
        boundPoint.setRect(x - 3, y - 3, 7, 7);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        g2.draw(boundPoint);
        g2.setStroke(new BasicStroke(0));
        g2.fill(boundPoint);
        resizePoints.add(boundPoint);

    }

    public void drawBound(Graphics g) {
        Rectangle2D bound = thisShape.getBounds2D();
        drawBoundPoint((int) bound.getMinX(), (int) bound.getMinY(), g);
        drawBoundPoint((int) bound.getMinX(), (int) bound.getMaxY(), g);
        drawBoundPoint((int) bound.getMaxX(), (int) bound.getMinY(), g);
        drawBoundPoint((int) bound.getMaxX(), (int) bound.getMaxY(), g);

    }

    public abstract void move(Point p, Point origin);

    public abstract void resize(Point p, Resizer r);

    @Override
    public String toString() {
        return getClass().toString();
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

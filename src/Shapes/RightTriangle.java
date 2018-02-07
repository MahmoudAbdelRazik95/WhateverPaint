/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import Tools.Resizer;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.Polygon;
import java.awt.RenderingHints;




public class RightTriangle extends Shapes {

    private int x1, x2, x3, y1, y2, y3;
    private Polygon triangle;

    public RightTriangle() {

    }

    @Override
    public void drawShape(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getColor());
        x1 = getxPos();
        x2 = x1;
        y1 = getyPos();
        y2 = y1 + (int) getLengths();
        y3 = y2;
        x3 = x1 + (int) getWidths();
        g2.setStroke(new BasicStroke(getPenSize()));
        triangle = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        g2.draw(triangle);
        setThisShape(triangle.getBounds2D());
        g2.setPaint(getFillColor());
        g2.fill(triangle);
        if (isSelected()) {
            drawBound(g2);
        }

    }

    @Override
    public void drawBound(Graphics g) {
        drawBoundPoint(x1, y1, g);
        drawBoundPoint(x2, y2, g);
        drawBoundPoint(x3, y3, g);

    }

    @Override
    public void move(Point p, Point o) {
        int baseX = (int) o.getX();
        int baseY = (int) o.getY();
        int dx = (int) (p.getX() - baseX);
        int dy = (int) (p.getY() - baseY);
        setxPos(getxPos() + dx);
        setyPos(getyPos() + dy);

    }

    @Override
    public void resize(Point p, Resizer r) {
        setX2((int) p.getX());
        setY2((int) p.getY());
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

}

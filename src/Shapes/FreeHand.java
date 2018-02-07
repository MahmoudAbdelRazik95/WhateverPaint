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
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;




public class FreeHand extends Shapes {

    protected ArrayList<Point> points = new ArrayList();
    protected int minX = 1000;
    protected int minY = 1000;
    protected int maxX = 0;
    protected int maxY = 0;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void drawShape(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getColor());
        g2.setStroke(new BasicStroke(getPenSize()));

        if (points.size() > 1) {
            for (int i = 0; i < points.size() - 1; i++) {
                g2.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i + 1).getX(), (int) points.get(i + 1).getY());
                if ((int) points.get(i).getX() >= maxX) {
                    maxX = (int) points.get(i).getX();
                }
                if ((int) points.get(i).getY() >= maxY) {
                    maxY = (int) points.get(i).getY();
                }
                if ((int) points.get(i).getX() < minX) {
                    minX = (int) points.get(i).getX();
                }
                if ((int) points.get(i).getY() < minY) {
                    minY = (int) points.get(i).getY();
                }
            }
        }
        Rectangle2D bound = new Rectangle2D.Double();
        bound.setFrame(minX, minY, maxX - minX, maxY - minY);
        setThisShape(bound);
        if (isSelected()) {
            drawBound(g);
        }

    }

    @Override
    public void setPoint(Point p) {
        points.add(p);

    }

    @Override
    public void move(Point p, Point o) {
        minX = 1000;
        minY = 1000;
        maxX = 0;
        maxY = 0;
        int baseX = (int) o.getX();
        int baseY = (int) o.getY();
        int dx = (int) (p.getX() - baseX);
        int dy = (int) (p.getY() - baseY);
        int x;
        int y;
        for (Point pp : points) {
            x = (int) (pp.getX() + dx);
            y = (int) (pp.getY() + dy);
            pp.setLocation(x, y);

        }

    }

    @Override
    public void resize(Point p, Resizer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;





public class Ellipse extends Shapes {

    protected Ellipse2D ellipse = new Ellipse2D.Double();

    public Ellipse() {
        setThisShape(ellipse);
    }

    @Override
    public void drawShape(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getColor());
        g2.setStroke(new BasicStroke(getPenSize()));
        ellipse.setFrame(Math.min(getWidths(), getxPos()), Math.min(getLengths(), getyPos()), Math.abs(getWidths() - getxPos()), Math.abs(getLengths() - getyPos()));
        g2.draw(ellipse);
        g2.setPaint(getFillColor());
        g2.fill(ellipse);
        if (isSelected()) {
            drawBound(g2);
        }

    }

    @Override
    public void move(Point p, Point o) {
        int baseX = (int) o.getX();
        int baseY = (int) o.getY();
        int dx = (int) (p.getX() - baseX);
        int dy = (int) (p.getY() - baseY);
        setxPos(getxPos() + dx);
        setyPos(getyPos() + dy);
        setWidths(getWidths() + dx);
        setLengths(getLengths() + dy);

    }

    @Override
    public void resize(Point p, Resizer r) {
      ArrayList<Rectangle2D> rs = getResizePoints();
        
            if(rs.get(0).contains(p))
            {
                
                    setxPos((int) p.getX());
                    setyPos((int) p.getY());}
                
                else if(rs.get(1).contains(p))
                {
                    setxPos((int) p.getX());
                    
                    setLengths(p.getY());
                }
                else if(rs.get(2).contains(p))
                {
                    setyPos((int) p.getY());
                    
                    setWidths(p.getX());
                }
                else 
                {
                    setWidths((int) p.getX());
                    setLengths((int) p.getY());
                    
                }
            
        }
}

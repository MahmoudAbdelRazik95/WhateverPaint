package Tools;

import Shapes.Shapes;
import Shapes.Shapes;
import ToolsPanels.Canvas;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;




public class Selector extends Shapes {

    private Rectangle2D rect = new Rectangle2D.Double();

    public Selector() {

    }

    @Override
    public void drawShape(Graphics g) {
        float dash1[] = {10.0f};
        BasicStroke dashed = new BasicStroke(3.0f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.SrcOver.derive(0.1f));

        g2.setStroke(dashed);
        rect.setRect(Math.min(getWidths(), getxPos()), Math.min(getLengths(), getyPos()), Math.abs(getWidths() - getxPos()), Math.abs(getLengths() - getyPos()));
        g2.draw(rect);
        g2.setColor(new Color(0, 191, 243));
        g2.fill(rect);
        g2.setComposite(AlphaComposite.SrcOver);
        select();

    }

    public void select() {
        Rectangle2D checker = new Rectangle2D.Double();

        for (Shapes s : Canvas.prevShapes) {
            checker = s.getThisShape().getBounds2D();
            if (rect.contains(checker)) {
                s.setSelected(true);

            }

        }

    }

    public void select(Point p) {
        for (Shapes s : Canvas.prevShapes) {
            if (s.containsPoint(p)) {
                s.setSelected(true);
            }
        }
    }

    public static void disposeSelection() {
        for (Shapes s : Canvas.prevShapes) {
            s.setSelected(false);
        }
    }

    @Override
    public void resize(Point p, Resizer r) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move(Point p, Point origin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

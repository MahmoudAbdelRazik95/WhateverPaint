/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolsPanels;

import Tools.Intersection;
import Tools.Filler;
import Shapes.Line;
import Shapes.FreeHand;
import Shapes.IsocelesTriangle;
import Tools.Mover;
import Tools.Picker;

import Tools.Resizer;
import Shapes.RightTriangle;
import Shapes.Shapes;
import WhateverPaint.MainWindow;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import Tools.Selector;
import UndoRedoManager.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.util.Stack;




public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener, Cloneable {

    private static Shapes s = new FreeHand();
    public static boolean whichColor = false;

    public static void setShape(Shapes s) {
        Canvas.s = s;
    }
    public static ArrayList<Shapes> prevShapes = new ArrayList();
    public static ArrayList<Intersection> intersects = new ArrayList();
    private boolean flag = false;
    private Point point;
    private static int stroke;
    private static Color color = Color.BLACK;
    private static Color bgColor = new Color(255, 255, 255, 0);
    private Color transparent = new Color(255, 255, 255, 0);
    public static ArrayList<Shapes> copyList = new ArrayList<Shapes>();
    public static Stack<ArrayList<Shapes>> undost = new Stack();
    public static Stack<ArrayList<Shapes>> redost = new Stack();
    
    public Canvas() {

        setBackground(color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

    }

    public static void setBgColor(Color bgColor) {
        Canvas.bgColor = bgColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shapes ss : prevShapes) {
            ss.drawShape(g);
        }
        
        s.drawShape(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Intersection i : intersects) {
            g2.setPaint(i.getColor());
            g2.fill(i.getArea());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Selector.disposeSelection();
        if (s instanceof Selector) {
            ((Selector) s).select(e.getPoint());
        }
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        flag = false;
        s.setxPos(e.getX());
        s.setyPos(e.getY());
        s.setColor(color);
        s.setPenSize(stroke);
        if (s instanceof Filler) {
            boolean intersect = false;

            ArrayList<Shapes> within = new ArrayList();
            for (Shapes ss : prevShapes) {
                if (ss.containsPoint(e.getPoint())) {
                    within.add(ss);
                }
            }
            if (within.size() < 2) {
                intersect = false;
            } else {
                intersect = true;
            }
            if (!intersect) {
                for (Shapes ss : prevShapes) {
                    if (ss.containsPoint(e.getPoint())) {
                        ss.setFillColor(bgColor);
                    }

                }
            } else {
                Area intersection = new Area(within.get(0).getThisShape());
                Area check;
                for (int i = 1; i < within.size(); i++) {
                    check = new Area(within.get(i).getThisShape());
                    intersection.intersect(check);
                }
                Color intersectColor = ColorBoxes.bgColor.getBackground();
                intersects.add(new Intersection(intersection, intersectColor));
            }
            repaint();
        } else if (s instanceof Mover) {
            point = e.getPoint();
        } else if (s instanceof Picker) {
            try {
                Robot robot = new Robot();
                color = robot.getPixelColor(e.getXOnScreen(), e.getYOnScreen());
                ColorBoxes.penColor.setBackground(color);
            } catch (AWTException ex) {
                Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            if (flag && !(s instanceof Selector) && !(s instanceof Mover) && !(s instanceof Resizer) && !(s instanceof Filler) && !(s instanceof Picker)) {
                prevShapes.add(s);
                redost.clear();

            } else {

                repaint();
            }
            ArrayList<Shapes> copyList = new ArrayList<Shapes>();
            for (int i = 0; i < prevShapes.size(); i++) {

                copyList.add((Shapes) prevShapes.get(i).clone());
            }
            undost.push(copyList);
            System.out.println(undost);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        flag = true;
        MainWindow.setPosition("Mouse Position :" + e.getX() + "," + e.getY());
        if (s instanceof FreeHand) {
            s.setPoint(e.getPoint());

        } else if (s instanceof Mover) {

            for (Shapes s2 : prevShapes) {
                if (s2.isSelected()) {
                    s2.move(e.getPoint(), point);

                }

            }
            point.x = e.getX();
            point.y = e.getY();

        } else if (s instanceof Resizer) {
            for (Shapes s2 : prevShapes) {
                if (s2.isSelected()) {
                    if (s2.getClass().getName().equalsIgnoreCase("Shapes.IsocelesTriangle") || s2.getClass().getName().equalsIgnoreCase("Shapes.RightTriangle")) {
                        s2.setWidths(e.getX() - s2.getxPos());
                        s2.setLengths(e.getY() - s2.getyPos());
                    }

                    s2.resize(e.getPoint(), (Resizer) s);
                }

            }
        } else if (s instanceof Line) {
            s.setWidths(e.getX());
            s.setLengths(e.getY());

        } else if (s instanceof RightTriangle || s instanceof IsocelesTriangle) {
            s.setWidths(e.getX() - s.getxPos());
            s.setLengths(e.getY() - s.getyPos());
        } else {
            s.setWidths(e.getX());
            s.setLengths(e.getY());

        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        MainWindow.setPosition("Mouse Position :" + e.getX() + "," + e.getY());

    }

    public static void setStroke(int stroke) {
        Canvas.stroke = stroke;
    }

    public static void setColor(Color color) {
        Canvas.color = color;
    }

    public static ArrayList<Shapes> getPrevShapes() {
        return prevShapes;
    }

    public static void setPrevShapes(ArrayList<Shapes> prevShapes) {
        Canvas.prevShapes = prevShapes;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == 127) {
            ArrayList<Shapes> temp = new ArrayList();
            for (Shapes ss : prevShapes) {
                if (!ss.isSelected()) {

                    temp.add(ss);
                }

            }
            prevShapes = temp;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

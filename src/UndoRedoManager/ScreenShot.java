/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UndoRedoManager;

import Shapes.Shapes;
import Tools.Intersection;
import java.util.ArrayList;



public class ScreenShot {

    private ArrayList<Shapes> shapes;
    private ArrayList<Intersection> intersections;

    public ArrayList<Shapes> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shapes> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

}

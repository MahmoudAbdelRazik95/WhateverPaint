/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.awt.Color;
import java.awt.geom.Area;




public class Intersection {
    private Area area;
    private Color color;

    public Intersection(Area area, Color color) {
        this.area = area;
        this.color = color;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}

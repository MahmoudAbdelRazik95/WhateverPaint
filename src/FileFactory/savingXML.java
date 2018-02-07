/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileFactory;

import Shapes.Circle;
import Shapes.Ellipse;
import Shapes.FreeHand;
import Shapes.IsocelesTriangle;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.RightTriangle;
import Shapes.Shapes;
import Shapes.Square;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.util.ArrayList;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import java.awt.Color;
import java.awt.Point;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;




public class savingXML {

    private String path;

    public savingXML(ArrayList<Shapes> list, String path) {
        try {
            this.path = path;
            this.savetoXMLfile(list);
            JFileChooser file = new JFileChooser();
            file.setVisible(true);
        } catch (ParserConfigurationException parse) {
        } catch (FileNotFoundException filenotfound) {
        } catch (IOException ioexception) {
        }
    }

    public void savetoXMLfile(ArrayList<Shapes> list) throws ParserConfigurationException, FileNotFoundException, IOException {
        Shapes s;
        int i;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //document
        Document xmldoc = db.newDocument();
        //structure
        //Element e = null;
        Element rootEle = xmldoc.createElement("Shapes");

        for (i = 0; i < list.size(); i++) {
            Element mainelement = xmldoc.createElement("type");
            s = list.get(i);
            String type = "";
            if (s instanceof Rectangle) {
                type = "Rectangle";
            }
            if (s instanceof Square) {
                type = "Square";
            }
            if (s instanceof Ellipse) {
                type = "Ellipse";
            }
            if (s instanceof Circle) {
                type = "Circle";
            }
            if (s instanceof Line) {
                type = "Line";
            }
            if (s instanceof IsocelesTriangle) {
                type = "IsocelesTriangle";
            }
            if (s instanceof RightTriangle) {
                type = "RightTriangle";
            }
            if (s instanceof FreeHand) {
                FreeHand f = (FreeHand) s;
                type = "FreeHand";
                ArrayList<Point> pointss = f.getPoints();
                int redcolour = s.getColor().getRed();
                int greencolour = s.getColor().getGreen();
                int bluecolour = s.getColor().getBlue();
                String redc = Integer.toString(redcolour);
                String greenc = Integer.toString(greencolour);
                String bluec = Integer.toString(bluecolour);
                String pensize = Integer.toString(s.getPenSize());

                mainelement.setAttribute("T", type);
                for (int k = 0; k < pointss.size(); k++) {
                    Point p;
                    p = pointss.get(k);
                    int x = p.x;
                    String xstring = Integer.toString(x);
                    int y = p.y;
                    String ystring = Integer.toString(y);
                    Element Point = xmldoc.createElement("Point");

                    Text xpointText = xmldoc.createTextNode(xstring);
                    Element xpoint = xmldoc.createElement("xpoint");
                    xpoint.appendChild(xpointText);
                    mainelement.appendChild(xpoint);
                    Text ypointText = xmldoc.createTextNode(ystring);
                    Element ypoint = xmldoc.createElement("ypoint");
                    ypoint.appendChild(ypointText);
                    mainelement.appendChild(ypoint);
                    Point.appendChild(xpoint);
                    Point.appendChild(ypoint);
                    mainelement.appendChild(Point);
                }
                Text pensizetext = xmldoc.createTextNode(pensize);
                Element PenSize1 = xmldoc.createElement("Pensize");
                PenSize1.appendChild(pensizetext);
                mainelement.appendChild(PenSize1);
                Element colour = xmldoc.createElement("colour");
                Text redtext = xmldoc.createTextNode(redc);
                Element red = xmldoc.createElement("redcomponent");
                red.appendChild(redtext);
                colour.appendChild(red);
                Text greentext = xmldoc.createTextNode(greenc);
                Element green = xmldoc.createElement("greencomponent");
                colour.appendChild(green);
                green.appendChild(greentext);
                Text bluetext = xmldoc.createTextNode(bluec);
                Element blue = xmldoc.createElement("bluecomponent");
                blue.appendChild(bluetext);
                colour.appendChild(blue);
                mainelement.appendChild(colour);

            } else {
                String xposition = Integer.toString(s.getxPos());
                String yposition = Integer.toString(s.getyPos());
                String widthh = Double.toString(s.getWidths());
                String lengthh = Double.toString(s.getLengths());
                int redcolour = s.getColor().getRed();
                int greencolour = s.getColor().getGreen();
                int bluecolour = s.getColor().getBlue();
                String redc = Integer.toString(redcolour);
                String greenc = Integer.toString(greencolour);
                String bluec = Integer.toString(bluecolour);
                String pensize = Integer.toString(s.getPenSize());
                mainelement.setAttribute("T", type);
                Text xpointText = xmldoc.createTextNode(xposition);
                Element xpoint = xmldoc.createElement("xpoint");
                xpoint.appendChild(xpointText);
                mainelement.appendChild(xpoint);
                Text ypointText = xmldoc.createTextNode(yposition);
                Element ypoint = xmldoc.createElement("ypoint");
                ypoint.appendChild(ypointText);
                mainelement.appendChild(ypoint);
                Text lengthText = xmldoc.createTextNode(lengthh);
                Element length = xmldoc.createElement("length");
                length.appendChild(lengthText);
                mainelement.appendChild(length);
                Text widthText = xmldoc.createTextNode(widthh);
                Element width = xmldoc.createElement("width");
                width.appendChild(widthText);
                mainelement.appendChild(width);
                Text pensizetext = xmldoc.createTextNode(pensize);
                Element PenSize1 = xmldoc.createElement("Pensize");
                PenSize1.appendChild(pensizetext);
                mainelement.appendChild(PenSize1);
                Element colour = xmldoc.createElement("colour");
                Text redtext = xmldoc.createTextNode(redc);
                Element red = xmldoc.createElement("redcomponent");
                red.appendChild(redtext);
                colour.appendChild(red);
                Text greentext = xmldoc.createTextNode(greenc);
                Element green = xmldoc.createElement("greencomponent");
                colour.appendChild(green);
                green.appendChild(greentext);
                Text bluetext = xmldoc.createTextNode(bluec);
                Element blue = xmldoc.createElement("bluecomponent");
                blue.appendChild(bluetext);
                colour.appendChild(blue);
                mainelement.appendChild(colour);
                redcolour = s.getFillColor().getRed();
                greencolour = s.getFillColor().getGreen();
                bluecolour = s.getFillColor().getBlue();
                int alphacolor = s.getFillColor().getAlpha();
                redc = Integer.toString(redcolour);
                greenc = Integer.toString(greencolour);
                bluec = Integer.toString(bluecolour);

                String alphac = Integer.toString(alphacolor);
                Element colourfill = xmldoc.createElement("colourfill");
                Text redfilltext = xmldoc.createTextNode(redc);
                Element redfill = xmldoc.createElement("redfillcomponent");
                redfill.appendChild(redfilltext);
                colourfill.appendChild(redfill);
                Text greenfilltext = xmldoc.createTextNode(greenc);
                Element greenfill = xmldoc.createElement("greenfillcomponent");
                colourfill.appendChild(greenfill);
                greenfill.appendChild(greenfilltext);
                Text bluefilltext = xmldoc.createTextNode(bluec);
                Element bluefill = xmldoc.createElement("bluefillcomponent");
                bluefill.appendChild(bluefilltext);
                colourfill.appendChild(bluefill);
                Text alphafilltext = xmldoc.createTextNode(alphac);
                Element alpha = xmldoc.createElement("alpha");
                alpha.appendChild(alphafilltext);
                colourfill.appendChild(alpha);
                mainelement.appendChild(colourfill);

            }
            rootEle.appendChild(mainelement);

        }
        xmldoc.appendChild(rootEle);

        OutputFormat outformat = new OutputFormat(xmldoc);
        outformat.setIndenting(true);

        File xmlfile = new File(path);

        FileOutputStream outstream = new FileOutputStream(xmlfile);

        XMLSerializer serializer = new XMLSerializer(outstream, outformat);

        serializer.serialize(xmldoc);
    }

}

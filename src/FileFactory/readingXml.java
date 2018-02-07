/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileFactory;

import java.awt.Color;
import java.awt.Point;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import Shapes.*;




public class readingXml {

    ArrayList<Shapes> shapes2 = new ArrayList<Shapes>();
    ArrayList<Point> pointss;
    private String path;

    public readingXml(String path) {
        try {
            this.path = path;
            this.readfromXMLfile();
        } catch (ParserConfigurationException | SAXException | IOException parse) {
        }
    }

    public void readfromXMLfile() throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        ArrayList<Shapes> shapes = new ArrayList<Shapes>();
        Shapes s = new Circle();
        String rootelement = doc.getDocumentElement().getNodeName();

        NodeList nList = doc.getElementsByTagName("type");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)//node is an element
            {
                Element eElement = (Element) nNode;
                String type = eElement.getAttribute("T");

                if (!type.equals("FreeHand")) {
                    String xpoint = eElement.getElementsByTagName("xpoint").item(0).getTextContent();

                    String ypoint = eElement.getElementsByTagName("ypoint").item(0).getTextContent();

                    String length = eElement.getElementsByTagName("length").item(0).getTextContent();

                    String width = eElement.getElementsByTagName("width").item(0).getTextContent();

                    String pensize = eElement.getElementsByTagName("Pensize").item(0).getTextContent();

                    String redcomponent = eElement.getElementsByTagName("redcomponent").item(0).getTextContent();

                    String greencomponent = eElement.getElementsByTagName("greencomponent").item(0).getTextContent();

                    String bluecomponent = eElement.getElementsByTagName("bluecomponent").item(0).getTextContent();

                    String redfillcomponent = eElement.getElementsByTagName("redfillcomponent").item(0).getTextContent();

                    String greenfillcomponent = eElement.getElementsByTagName("greenfillcomponent").item(0).getTextContent();

                    String bluefillcomponent = eElement.getElementsByTagName("bluefillcomponent").item(0).getTextContent();
                    String alpha = eElement.getElementsByTagName("alpha").item(0).getTextContent();
                    int xpointint = Integer.parseInt(xpoint);
                    int ypointint = Integer.parseInt(ypoint);
                    int pensizeint = Integer.parseInt(pensize);
                    double lengthdouble = Double.parseDouble(length);
                    double widthdouble = Double.parseDouble(width);
                    int redcompint = Integer.parseInt(redcomponent);
                    int bluecompint = Integer.parseInt(bluecomponent);
                    int greencompint = Integer.parseInt(greencomponent);
                    Color colour = new Color(redcompint, greencompint, bluecompint);
                    int redfillcompint = Integer.parseInt(redfillcomponent);
                    int bluefillcompint = Integer.parseInt(bluefillcomponent);
                    int greenfillcompint = Integer.parseInt(greenfillcomponent);
                    int alphafill = Integer.parseInt(alpha);
                    Color colourfill = new Color(redfillcompint, greenfillcompint, bluefillcompint, alphafill);
                    if (type.equals("Rectangle")) {
                        s = new Rectangle();
                    } else if (type.equals("Square")) {
                        s = new Square();
                    } else if (type.equals("Ellipse")) {
                        s = new Ellipse();
                    } else if (type.equals("Circle")) {
                        s = new Circle();
                    } else if (type.equals("Line")) {
                        s = new Line();
                    } else if (type.equals("IsocelesTriangle")) {
                        s = new IsocelesTriangle();
                    } else if (type.equals("RightTriangle")) {
                        s = new RightTriangle();
                    }
                    s.setxPos(xpointint);
                    s.setyPos(ypointint);
                    s.setLengths(lengthdouble);
                    s.setWidths(widthdouble);
                    s.setColor(colour);
                    s.setFillColor(colourfill);
                    shapes.add(s);

                } else {
                    s = new FreeHand();
                    pointss = new ArrayList<>();
                    pointss.clear();
                    String pensize = eElement.getElementsByTagName("Pensize").item(0).getTextContent();

                    String redcomponent = eElement.getElementsByTagName("redcomponent").item(0).getTextContent();

                    String greencomponent = eElement.getElementsByTagName("greencomponent").item(0).getTextContent();

                    String bluecomponent = eElement.getElementsByTagName("bluecomponent").item(0).getTextContent();

                    NodeList nodes = doc.getElementsByTagName("Point");
                    for (int k = 0; k < nodes.getLength(); k++) {
                        Element node = (Element) nodes.item(k);

                        String xpoint = node.getElementsByTagName("xpoint").item(0).getTextContent();
                        String ypoint = node.getElementsByTagName("ypoint").item(0).getTextContent();

                        Point p = new Point(Integer.parseInt(xpoint), Integer.parseInt(ypoint));
                        pointss.add(p);

                        ((FreeHand) s).setPoints(pointss);
                    }
                    int pensizeint = Integer.parseInt(pensize);
                    int redcompint = Integer.parseInt(redcomponent);
                    int bluecompint = Integer.parseInt(bluecomponent);
                    int greencompint = Integer.parseInt(greencomponent);
                    Color colour = new Color(redcompint, greencompint, bluecompint);
                    s.setColor(colour);
                    s.setPenSize(pensizeint);
                    shapes.add(s);
                }

            }
        }
        shapes2 = shapes;
    }

    public ArrayList<Shapes> getShapes2() {
        return shapes2;
    }

}

package fr.miage.estorymap.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MFCXMLReader {

    private String filename;

    public MFCXMLReader(String filename) {
        this.filename = filename;
    }

    public List<String> getAllFluxName() {
        ArrayList<String> fluxName = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(this.filename));

            doc.getDocumentElement().normalize();

            NodeList mxCellList = doc.getElementsByTagName("mxCell");

            for (int i = 0; i < mxCellList.getLength(); i ++) {
                Node node = mxCellList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String value = element.getAttribute("value");

                    if (value.matches("F\\d+ : [a-zA-Z ]+")) {
                        int separatorIdx = value.indexOf(':');
                        fluxName.add(value.substring(0, separatorIdx - 1));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fluxName;
    }

    public List<String> getAllFluxAttribute() {
        ArrayList<String> fluxAttribute = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(this.filename));

            doc.getDocumentElement().normalize();

            NodeList mxCellList = doc.getElementsByTagName("mxCell");

            for (int i = 0; i < mxCellList.getLength(); i ++) {
                Node node = mxCellList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String value = element.getAttribute("value");

                    if (value.matches("F\\d+ : [a-zA-Z ]+")) {
                        int separatorIdx = value.indexOf(':');
                        fluxAttribute.addAll(List.of((value.substring(separatorIdx + 2)).split(" ")));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fluxAttribute;
    }

    public static void main(String[] args) {
        MFCXMLReader mxr = new MFCXMLReader("src/main/resources/MFC.drawio.xml");
        System.out.println(mxr.getAllFluxName());
        System.out.println(mxr.getAllFluxAttribute());
    }

}

package fr.miage.estorymap.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MCDXMLReader {

    public MCDXMLReader() {
    }

    public List<String> getAllAttribut(String filename) {
        ArrayList<String> attributs = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));

            doc.getDocumentElement().normalize();

            NodeList attributNodes = doc.getElementsByTagName("attribut");

            for (int i = 0; i < attributNodes.getLength(); i ++) {
                Node node = attributNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    attributs.add(element.getAttribute("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attributs;
    }

    public static void main(String[] args) {
        MCDXMLReader mxr = new MCDXMLReader();
        System.out.println(mxr.getAllAttribut("src/main/resources/mcd.xml"));
    }
}

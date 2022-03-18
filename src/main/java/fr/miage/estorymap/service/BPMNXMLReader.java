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
public class BPMNXMLReader {

    public BPMNXMLReader() {
    }

    public List<String> getAllFluxName(String filename) {
        ArrayList<String> fluxName = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));

            doc.getDocumentElement().normalize();

            fluxName.addAll(getName(doc.getElementsByTagName("bpmn:startEvent")));
            fluxName.addAll(getName(doc.getElementsByTagName("bpmn:intermediateCatchEvent")));
            fluxName.addAll(getName(doc.getElementsByTagName("bpmn:intermediateThrowEvent")));
            fluxName.addAll(getName(doc.getElementsByTagName("bpmn:endEvent")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fluxName;
    }

    private ArrayList<String> getName(NodeList nodeList) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i ++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getAttribute("name");
                int separatorIdx = name.indexOf(':');
                result.add(name.substring(0, separatorIdx - 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BPMNXMLReader bxr = new BPMNXMLReader();
        System.out.println(bxr.getAllFluxName("src/main/resources/BPMN.bpmn"));
    }
}

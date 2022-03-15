package fr.miage.estorymap.service;

import java.util.List;

public class Verificator {

    private BPMNXMLReader bpmnxmlReader;
    private MCDXMLReader mcdxmlReader;
    private MFCXMLReader mfcxmlReader;

    public Verificator() {
        bpmnxmlReader = new BPMNXMLReader();
        mcdxmlReader = new MCDXMLReader();
        mfcxmlReader = new MFCXMLReader();
    }

    public String verify() {
        final StringBuilder builder = new StringBuilder();
        final List<String> bpmnFluxName = bpmnxmlReader.getAllFluxName("src/main/resources/BPMN.bpmn");
        final List<String> mcdAttribut = mcdxmlReader.getAllAttribut("src/main/resources/mcd.xml");
        final List<String> mfcFluxName = mfcxmlReader.getAllFluxName("src/main/resources/MFC.drawio.xml");
        final List<String> mfcAttribut = mfcxmlReader.getAllFluxAttribute("src/main/resources/MFC.drawio.xml");

        compare(mfcFluxName, "MFC", bpmnFluxName, "BPMN", builder);
        compare(mfcAttribut, "MFC", mcdAttribut, "MCD", builder);
        compare(bpmnFluxName, "BPMN", mfcFluxName, "MFC", builder);
        compare(mcdAttribut, "MCD", mfcAttribut, "MFC", builder);

        if (builder.toString().equals("")) {
            builder.append("Aucune erreur trouvée dans le projet.");
        }

        return builder.toString();
    }

    private void compare(List<String> primary, String primaryName, List<String> secondary, String secondaryName, StringBuilder builder) {
        for (String s : primary) {
            if (!secondary.contains(s)) {
                builder.append(String.format("%c %s présent dans le %s mais pas dans le %s.%n", s, primaryName, secondaryName));
            }
        }
    }

    public static void main(String[] args) {
        Verificator verificator = new Verificator();
        System.out.println(verificator.verify());
    }
}

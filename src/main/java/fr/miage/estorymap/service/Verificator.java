package fr.miage.estorymap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Verificator {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private BPMNXMLReader bpmnxmlReader;
    private MCDXMLReader mcdxmlReader;
    private MFCXMLReader mfcxmlReader;

    public Verificator() {
        bpmnxmlReader = new BPMNXMLReader();
        mcdxmlReader = new MCDXMLReader();
        mfcxmlReader = new MFCXMLReader();
    }

    public String verify(String bpmnPath, String mfcPath, String mcdPath) {
        final StringBuilder builder = new StringBuilder();
        final List<String> bpmnFluxName = bpmnxmlReader.getAllFluxName(bpmnPath);
        final List<String> mcdAttribut = mcdxmlReader.getAllAttribut(mcdPath);
        final List<String> mfcFluxName = mfcxmlReader.getAllFluxName(mfcPath);
        final List<String> mfcAttribut = mfcxmlReader.getAllFluxAttribute(mfcPath);

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
                builder.append(String.format("%s présent dans le %s mais pas dans le %s.%n", s, primaryName, secondaryName));
            }
        }
    }
}

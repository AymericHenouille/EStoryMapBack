package fr.miage.estorymap.service;

import fr.miage.estorymap.component.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Response verify(String bpmnPath, String mfcPath, String mcdPath) {
        final List<String> messages = new ArrayList<>();

        final List<String> bpmnFluxName = bpmnxmlReader.getAllFluxName(bpmnPath);
        final List<String> mcdAttribut = mcdxmlReader.getAllAttribut(mcdPath);
        final List<String> mfcFluxName = mfcxmlReader.getAllFluxName(mfcPath);
        final List<String> mfcAttribut = mfcxmlReader.getAllFluxAttribute(mfcPath);

        final double totalRelation = bpmnFluxName.size() + mcdAttribut.size() + mfcFluxName.size() + mfcAttribut.size();
        double nbError = 0;

        nbError += compare("Le flux", mfcFluxName, "MFC", bpmnFluxName, "BPMN", messages);
        nbError += compare("L'attribut", mfcAttribut, "MFC", mcdAttribut, "MCD", messages);
        nbError += compare("Le flux", bpmnFluxName, "BPMN", mfcFluxName, "MFC", messages);
        nbError += compare("L'attribut", mcdAttribut, "MCD", mfcAttribut, "MFC", messages);

        if (messages.isEmpty()) {
            messages.add("Aucune erreur trouvée dans le projet.");
        }

        final double percent = (1 - (nbError / totalRelation)) * 100;

        //builder.append(nbError + " " + totalRelation + " " + (double) (nbError / totalRelation) + " " + (1 - (nbError / totalRelation)) + " " + (1 - (nbError / totalRelation)) * 100 + "\n");
        //builder.append(String.format("Le projet est valide à %.2f%%", (1 - (nbError / totalRelation)) * 100));

        return new Response(messages, percent);
    }

    private int compare(String type, List<String> primary, String primaryName, List<String> secondary, String secondaryName, List<String> messages) {
        int nbError = 0;

        for (String s : primary) {
            if (!secondary.contains(s)) {
                messages.add(String.format("%s '%s' est présent dans le %s mais pas dans le %s.", type, s, primaryName, secondaryName));
                nbError ++;
            }
        }
        return nbError;
    }

    public static void main(String[] args) {
        Verificator verificator = new Verificator();

        final String bpmnPath = "src/main/resources/BPMN.bpmn";
        final String mfcPath = "src/main/resources/MFC.drawio.xml";
        final String mcdPath = "src/main/resources/mcd.xml";

        Response response = verificator.verify(bpmnPath, mfcPath, mcdPath);
        for (String s : response.getMessages()) {
            System.out.println(s);
        }
    }
}
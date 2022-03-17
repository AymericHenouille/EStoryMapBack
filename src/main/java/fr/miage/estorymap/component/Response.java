package fr.miage.estorymap.component;

import java.util.List;

public class Response {

    private List<String> messages;
    private double percent;

    public Response(List<String> messages, double percent) {
        this.messages = messages;
        this.percent = percent;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}

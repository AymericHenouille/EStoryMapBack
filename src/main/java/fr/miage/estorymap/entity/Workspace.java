package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column(nullable = true)
    private Character emoticon;

    protected Workspace() { }

    public Workspace(String label, String color, char emoticon) {
        this.label = label;
        this.color = color;
        this.emoticon = emoticon;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }

    public char getEmoticon() {
        return emoticon;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", emoticon=" + emoticon +
                '}';
    }
}

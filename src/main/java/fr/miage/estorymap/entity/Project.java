package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idp")
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column()
    private String emoticon;

    @Column()
    private String cover;

    protected Project() { }

    public Project(String label, String color, String emoticon) {
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

    public String getEmoticon() {
        return emoticon;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", emoticon='" + emoticon + '\'' +
                '}';
    }
}

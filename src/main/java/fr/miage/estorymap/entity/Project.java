package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idp")
    private long id;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column()
    private String emoticon;

    @Column()
    private String cover;

    protected Project() { }

    public Project(String label, String color, String emoticon, String cover) {
        this.label = label;
        this.color = color;
        this.emoticon = emoticon;
        this.cover = cover;
    }

    public long getId() {
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

    public String getCover() {
        return cover;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(label, project.label) && Objects.equals(color, project.color) && Objects.equals(emoticon, project.emoticon) && Objects.equals(cover, project.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, color, emoticon, cover);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", emoticon='" + emoticon + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}

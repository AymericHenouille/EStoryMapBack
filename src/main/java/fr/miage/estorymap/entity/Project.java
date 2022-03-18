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

    @Column()
    private String bpmnName;

    @Column
    private String mfcName;

    @Column
    private String mcdName;

    protected Project() { }

    public Project(String label, String color, String emoticon, String cover, String bpmnName, String mfcName, String mcdName) {
        this.label = label;
        this.color = color;
        this.emoticon = emoticon;
        this.cover = cover;
        this.bpmnName = bpmnName;
        this.mfcName = mfcName;
        this.mcdName = mcdName;
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

    public String getBpmnName() {
        return bpmnName;
    }

    public String getMfcName() {
        return mfcName;
    }

    public String getMcdName() {
        return mcdName;
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

    public void setBpmnName(String bpmnName) {
        this.bpmnName = bpmnName;
    }

    public void setMfcName(String mfcName) {
        this.mfcName = mfcName;
    }

    public void setMcdName(String mcdName) {
        this.mcdName = mcdName;
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

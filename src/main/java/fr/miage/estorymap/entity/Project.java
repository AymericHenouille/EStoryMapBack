package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idp")
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_workspace_id", referencedColumnName = "idw", nullable = false)
    private Workspace workspace;

    protected Project() {}

    public Project(String label, String color, String image, Workspace workspace) {
        this.label = label;
        this.color = color;
        this.image = image;
        this.workspace = workspace;
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

    public String getImage() {
        return image;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                ", workspace=" + workspace +
                '}';
    }
}

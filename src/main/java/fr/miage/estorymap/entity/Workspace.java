package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idw;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column(nullable = true)
    private char emoji;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_user_id", referencedColumnName = "idu", nullable = false)
    private User owner;

    @ManyToMany(mappedBy = "workspaces")
    private Set<User> users;

    protected Workspace() {}

    public Workspace(String label, String color, char emoji, User owner) {
        this.label = label;
        this.color = color;
        this.emoji = emoji;
        this.owner = owner;
    }

    public Long getIdw() {
        return idw;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }

    public char getEmoji() {
        return emoji;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "idw=" + idw +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", emoji=" + emoji +
                ", owner=" + owner +
                '}';
    }
}

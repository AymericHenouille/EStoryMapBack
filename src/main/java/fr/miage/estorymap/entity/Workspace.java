package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idw")
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column()
    private Character emoticon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "idu")
    private User owner;

    @ManyToMany(mappedBy = "workspaces")
    private Set<User> users;

    protected Workspace() { }

    public Workspace(String label, String color, char emoticon, User owner) {
        this.label = label;
        this.color = color;
        this.emoticon = emoticon;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public Set<User> getUsers() {
        return users;
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

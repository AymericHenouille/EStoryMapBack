package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "shared_workspaces",
            joinColumns = @JoinColumn(name = "workspace_id", referencedColumnName = "idw"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idu")
    )
    private List<User> users;

    protected Workspace() { }

    public Workspace(String label, String color, char emoticon, User owner, List<User> users) {
        this.label = label;
        this.color = color;
        this.emoticon = emoticon;
        this.owner = owner;
        this.users = users;
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

    public List<User> getUsers() {
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

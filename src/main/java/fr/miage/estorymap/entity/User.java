package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "idu")
    private String id;

    @ManyToMany
    @JoinTable(
            name = "shared_workspaces",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id")
    )
    private Set<Workspace> workspaces;

    protected User() { }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Set<Workspace> getWorkspaces() {
        return workspaces;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}

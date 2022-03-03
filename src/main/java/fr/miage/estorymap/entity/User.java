package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String idu;

    @ManyToMany
    @JoinTable(
            name = "workspaces",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id"))
    private Set<Workspace> workspaces;

    protected User() {}

    public User(String idu) {
        this.idu = idu;
    }

    public String getIdu() {
        return idu;
    }

    @Override
    public String toString() {
        return "User{" +
                "idu='" + idu + '\'' +
                '}';
    }
}

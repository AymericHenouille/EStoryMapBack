package fr.miage.estorymap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String idu;

    @Column
    private String mail;

    @ManyToMany
    @JoinTable(
            name = "workspaces",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id"))
    private Set<Workspace> workspaces;

    protected User() {}

    public User(String idu, String mail) {
        this.idu = idu;
        this.mail = mail;
    }

    public String getIdu() {
        return idu;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "idu='" + idu + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}

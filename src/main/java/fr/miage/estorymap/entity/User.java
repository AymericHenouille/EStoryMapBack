package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String idu;

    private String mail;

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

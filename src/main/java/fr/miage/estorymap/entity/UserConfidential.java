package fr.miage.estorymap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "user_confidential")
public class UserConfidential {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idu")
    private User user;

    @Column
    private String password;

    public UserConfidential() { }

    public UserConfidential(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

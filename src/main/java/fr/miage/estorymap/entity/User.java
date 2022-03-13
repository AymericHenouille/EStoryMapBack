package fr.miage.estorymap.entity;

import fr.miage.estorymap.component.RegistrationRequest;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idu")
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    protected User() { }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(RegistrationRequest registrationRequest) {
        this(registrationRequest.getName(), registrationRequest.getEmail());
    }


    public void update(User user) {
        this.name = user.name;
        this.email = user.email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            '}';
    }

}

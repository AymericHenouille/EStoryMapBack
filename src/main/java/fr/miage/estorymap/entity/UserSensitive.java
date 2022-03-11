package fr.miage.estorymap.entity;

import fr.miage.estorymap.component.RegistrationRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_sensitive")
public class UserSensitive implements Serializable {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column
    private String password;

    public UserSensitive() { }

    public UserSensitive(long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserSensitive(RegistrationRequest registrationRequest) {
        this(-1, registrationRequest.getPassword());
    }

    public long getUserId(long id) {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void bind(User user) {
        this.setUserId(user.getId());
    }

    public void encodePassword(BCryptPasswordEncoder encoder) {
        this.setPassword(encoder.encode(this.getPassword()));
    }
}

package fr.miage.estorymap.entity;

import com.sun.istack.NotNull;

public class UserDTO {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public UserDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package fr.miage.estorymap.component;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.UserSensitive;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final UserSensitive userSensitive;

    public CustomUserDetails(User user, UserSensitive userSensitive) {
        this.user = user;
        this.userSensitive = userSensitive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return userSensitive.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package by.kastapravau.jwtapp.security;

import by.kastapravau.jwtapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {

    private String userName;
    private String password;

    public SecurityUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static SecurityUser create(User user) {
        if (user == null) {
            return null;
        }
        return new SecurityUser(user.getLogin(), user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

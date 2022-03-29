package mindswap.academy.app.service;

import lombok.AllArgsConstructor;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

@AllArgsConstructor
public class CustomUserDetailsImplementation implements UserDetails {


    private User user;

    @Autowired
    private AuthenticationService authenticationService;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authenticationService.getAuthorities(user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
